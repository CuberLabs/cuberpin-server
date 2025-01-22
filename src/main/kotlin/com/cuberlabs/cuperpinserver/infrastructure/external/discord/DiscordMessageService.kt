package com.cuberlabs.cuperpinserver.infrastructure.external.discord

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.Bank
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.ChargeStatus
import com.cuberlabs.cuperpinserver.domain.giftcardcharge.repository.GiftCardChargeRepository
import com.cuberlabs.cuperpinserver.infrastructure.exception.BusinessLogicException
import com.cuberlabs.cuperpinserver.infrastructure.websocket.service.ChargeSocketService
import discord4j.common.util.Snowflake
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.interaction.ButtonInteractionEvent
import discord4j.core.`object`.component.ActionRow
import discord4j.core.`object`.component.Button
import discord4j.core.`object`.entity.channel.TextChannel
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.util.*
import javax.transaction.Transactional

@Service
class DiscordMessageService(
    private val client: GatewayDiscordClient,
    private val chargeRepository: GiftCardChargeRepository,
    private val chargeSocketService: ChargeSocketService
) {
    @Value("\${discord.channel-id}")
    var channelId: String = ""

    @Transactional
    fun sendDepositRequestMessage(bank: String, accountNumber: String, amount: Int, chargeId: UUID) {
        val button = Button.success("confirm", "입금 완료")
        client.getChannelById(Snowflake.of(channelId))
            .ofType(TextChannel::class.java)
            .publishOn(Schedulers.boundedElastic())
            .flatMap { channel ->
                val createMessageMono = channel.createMessage { spec ->
                    spec.setContent("입금 요청이 들어왔습니다. $bank 에서 $amount 원을 입금해 주세요.")
                    spec.setComponents(
                        ActionRow.of(button)
                    )
                }

                val clickListener = client.on(
                    ButtonInteractionEvent::class.java
                ) { event: ButtonInteractionEvent ->
                    if (event.customId == "confirm") {
                        val giftCardCharge = chargeRepository.findByIdOrNull(chargeId)
                            ?: throw BusinessLogicException.GIFT_CARD_CHARGE_NOT_FOUND
                        giftCardCharge.updateTotalChargeStatus(true)
                        chargeSocketService.changeChargeStatus(giftCardCharge.id!!, giftCardCharge.chargeStatus, giftCardCharge.depositAmount.toInt())
                        return@on event.reply("처리되었습니다.").withEphemeral(true)
                    } else {
                        return@on Mono.empty<Void>()
                    }
                }.then()

                return@flatMap createMessageMono.then(clickListener)
            }
            .subscribe()
    }
}
