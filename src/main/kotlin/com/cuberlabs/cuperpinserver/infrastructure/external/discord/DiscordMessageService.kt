package com.cuberlabs.cuperpinserver.infrastructure.external.discord

import com.cuberlabs.cuperpinserver.domain.giftcardcharge.entity.vo.Bank
import discord4j.common.util.Snowflake
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.interaction.ButtonInteractionEvent
import discord4j.core.`object`.component.ActionRow
import discord4j.core.`object`.component.Button
import discord4j.core.`object`.entity.channel.TextChannel
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class DiscordMessageService(
    private val client: GatewayDiscordClient,
) {
    @Value("\${discord.channel-id}")
    var channelId: String = ""

    fun sendDepositRequestMessage(bank: String, accountNumber: String, amount: Int) {
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
