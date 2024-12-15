package com.cuberlabs.cuperpinserver.infrastructure.feign.store.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class GetProductOrdersResponse(
    val timestamp: String,
    val traceId: String,
    val data: Data,
)

data class Data(
    val contents: List<Content>,
    val pagination: Pagination,
)

data class Content(
    val productOrderId: String,
    val content: Content2,
)

data class Content2(
    val order: Order,
    val productOrder: ProductOrder,
    val beforeClaim: BeforeClaim,
    val currentClaim: CurrentClaim,
    val completedClaims: List<CompletedClaim>,
    val delivery: Delivery,
)

data class Order(
    val chargeAmountPaymentAmount: Long,
    val checkoutAccumulationPaymentAmount: Long,
    val generalPaymentAmount: Long,
    val naverMileagePaymentAmount: Long,
    val orderDate: String,
    val orderDiscountAmount: Long,
    val orderId: String,
    val ordererId: String,
    val ordererName: String,
    val ordererTel: String,
    val paymentDate: String,
    val paymentDueDate: String,
    val paymentMeans: String,
    val isDeliveryMemoParticularInput: String,
    val payLocationType: String,
    val ordererNo: String,
    val payLaterPaymentAmount: Long,
)

data class ProductOrder(
    val claimStatus: String,
    val claimType: String,
    val decisionDate: String,
    val delayedDispatchDetailedReason: String,
    val delayedDispatchReason: String,
    val deliveryDiscountAmount: Long,
    val deliveryFeeAmount: Long,
    val deliveryPolicyType: String,
    val expectedDeliveryMethod: String,
    val freeGift: String,
    val mallId: String,
    val optionCode: String,
    val optionPrice: Long,
    val packageNumber: String,
    val placeOrderDate: String,
    val placeOrderStatus: String,
    val productClass: String,
    val productDiscountAmount: Long,
    val initialProductDiscountAmount: Long,
    val remainProductDiscountAmount: Long,
    val groupProductId: Long,
    val productId: String,
    val originalProductId: String,
    val merchantChannelId: String,
    val productName: String,
    val productOption: String,
    val productOrderId: String,
    val productOrderStatus: String,
    val quantity: Long,
    val initialQuantity: Long,
    val remainQuantity: Long,
    val sectionDeliveryFee: Long,
    val sellerProductCode: String,
    val shippingAddress: ShippingAddress,
    val shippingStartDate: String,
    val shippingDueDate: String,
    val shippingFeeType: String,
    val shippingMemo: String,
    val takingAddress: TakingAddress,
    val totalPaymentAmount: Long,
    val initialPaymentAmount: Long,
    val remainPaymentAmount: Long,
    val totalProductAmount: Long,
    val initialProductAmount: Long,
    val remainProductAmount: Long,
    val unitPrice: Long,
    val sellerBurdenDiscountAmount: Long,
    val commissionRatingType: String,
    val commissionPrePayStatus: String,
    val paymentCommission: Long,
    val saleCommission: Long,
    val expectedSettlementAmount: Long,
    val inflowPath: String,
    val inflowPathAdd: String,
    val itemNo: String,
    val optionManageCode: String,
    val sellerCustomCode1: String,
    val sellerCustomCode2: String,
    val claimId: String,
    val channelCommission: Long,
    val individualCustomUniqueCode: String,
    val productImediateDiscountAmount: Long,
    val productProductDiscountAmount: Long,
    val productMultiplePurchaseDiscountAmount: Long,
    val sellerBurdenImediateDiscountAmount: Long,
    val sellerBurdenProductDiscountAmount: Long,
    val sellerBurdenMultiplePurchaseDiscountAmount: Long,
    val knowledgeShoppingSellingInterlockCommission: Long,
    val giftReceivingStatus: String,
    val sellerBurdenStoreDiscountAmount: Long,
    val sellerBurdenMultiplePurchaseDiscountType: String,
    val logisticsCompanyId: String,
    val logisticsCenterId: String,
    val hopeDelivery: HopeDelivery,
    val arrivalGuaranteeDate: String,
    val deliveryAttributeType: String,
    val expectedDeliveryCompany: String,
)

data class ShippingAddress(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
    val pickupLocationType: String,
    val pickupLocationContent: String,
    val entryMethod: String,
    val entryMethodContent: String,
)

data class TakingAddress(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
)

data class HopeDelivery(
    val region: String,
    val additionalFee: Long,
    val hopeDeliveryYmd: String,
    val hopeDeliveryHm: String,
    val changeReason: String,
    val changer: String,
)

data class BeforeClaim(
    val exchange: Exchange,
)

data class Exchange(
    val claimId: String,
    val claimDeliveryFeeDemandAmount: Long,
    val claimDeliveryFeePayMeans: String,
    val claimDeliveryFeePayMethod: String,
    val claimRequestDate: String,
    val claimStatus: String,
    val collectAddress: CollectAddress,
    val collectCompletedDate: String,
    val collectDeliveryCompany: String,
    val collectDeliveryMethod: String,
    val collectStatus: String,
    val collectTrackingNumber: String,
    val etcFeeDemandAmount: Long,
    val etcFeePayMeans: String,
    val etcFeePayMethod: String,
    val exchangeDetailedReason: String,
    val exchangeReason: String,
    val holdbackDetailedReason: String,
    val holdbackReason: String,
    val holdbackStatus: String,
    val reDeliveryMethod: String,
    val reDeliveryStatus: String,
    val reDeliveryCompany: String,
    val reDeliveryTrackingNumber: String,
    val reDeliveryAddress: ReDeliveryAddress,
    val requestChannel: String,
    val requestQuantity: Long,
    val returnReceiveAddress: ReturnReceiveAddress,
    val holdbackConfigDate: String,
    val holdbackConfigurer: String,
    val holdbackReleaseDate: String,
    val holdbackReleaser: String,
    val claimDeliveryFeeProductOrderIds: String,
    val reDeliveryOperationDate: String,
    val claimDeliveryFeeDiscountAmount: Long,
    val remoteAreaCostChargeAmount: Long,
)

data class CollectAddress(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
)

data class ReDeliveryAddress(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
)

data class ReturnReceiveAddress(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
    val logisticsCenterId: String,
)

data class CurrentClaim(
    val cancel: Cancel,
    @JsonProperty("return")
    val return_field: Return,
    val exchange: Exchange2,
)

data class Cancel(
    val claimId: String,
    val cancelApprovalDate: String,
    val cancelCompletedDate: String,
    val cancelDetailedReason: String,
    val cancelReason: String,
    val claimRequestDate: String,
    val claimStatus: String,
    val refundExpectedDate: String,
    val refundStandbyReason: String,
    val refundStandbyStatus: String,
    val requestChannel: String,
    val requestQuantity: Long,
)

data class Return(
    val claimId: String,
    val claimDeliveryFeeDemandAmount: Long,
    val claimDeliveryFeePayMeans: String,
    val claimDeliveryFeePayMethod: String,
    val claimRequestDate: String,
    val claimStatus: String,
    val collectAddress: CollectAddress2,
    val collectCompletedDate: String,
    val collectDeliveryCompany: String,
    val collectDeliveryMethod: String,
    val collectStatus: String,
    val collectTrackingNumber: String,
    val etcFeeDemandAmount: Long,
    val etcFeePayMeans: String,
    val etcFeePayMethod: String,
    val holdbackDetailedReason: String,
    val holdbackReason: String,
    val holdbackStatus: String,
    val refundExpectedDate: String,
    val refundStandbyReason: String,
    val refundStandbyStatus: String,
    val requestChannel: String,
    val requestQuantity: Long,
    val returnDetailedReason: String,
    val returnReason: String,
    val returnReceiveAddress: ReturnReceiveAddress2,
    val returnCompletedDate: String,
    val holdbackConfigDate: String,
    val holdbackConfigurer: String,
    val holdbackReleaseDate: String,
    val holdbackReleaser: String,
    val claimDeliveryFeeProductOrderIds: String,
    val claimDeliveryFeeDiscountAmount: Long,
    val remoteAreaCostChargeAmount: Long,
)

data class CollectAddress2(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
)

data class ReturnReceiveAddress2(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
    val logisticsCenterId: String,
)

data class Exchange2(
    val claimId: String,
    val claimDeliveryFeeDemandAmount: Long,
    val claimDeliveryFeePayMeans: String,
    val claimDeliveryFeePayMethod: String,
    val claimRequestDate: String,
    val claimStatus: String,
    val collectAddress: CollectAddress3,
    val collectCompletedDate: String,
    val collectDeliveryCompany: String,
    val collectDeliveryMethod: String,
    val collectStatus: String,
    val collectTrackingNumber: String,
    val etcFeeDemandAmount: Long,
    val etcFeePayMeans: String,
    val etcFeePayMethod: String,
    val exchangeDetailedReason: String,
    val exchangeReason: String,
    val holdbackDetailedReason: String,
    val holdbackReason: String,
    val holdbackStatus: String,
    val reDeliveryMethod: String,
    val reDeliveryStatus: String,
    val reDeliveryCompany: String,
    val reDeliveryTrackingNumber: String,
    val reDeliveryAddress: ReDeliveryAddress2,
    val requestChannel: String,
    val requestQuantity: Long,
    val returnReceiveAddress: ReturnReceiveAddress3,
    val holdbackConfigDate: String,
    val holdbackConfigurer: String,
    val holdbackReleaseDate: String,
    val holdbackReleaser: String,
    val claimDeliveryFeeProductOrderIds: String,
    val reDeliveryOperationDate: String,
    val claimDeliveryFeeDiscountAmount: Long,
    val remoteAreaCostChargeAmount: Long,
)

data class CollectAddress3(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
)

data class ReDeliveryAddress2(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
)

data class ReturnReceiveAddress3(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
    val logisticsCenterId: String,
)

data class CompletedClaim(
    val claimType: String,
    val claimId: String,
    val claimStatus: String,
    val claimRequestDate: String,
    val requestChannel: String,
    val claimRequestDetailContent: String,
    val claimRequestReason: String,
    val refundExpectedDate: String,
    val refundStandbyReason: String,
    val refundStandbyStatus: String,
    val requestQuantity: Long,
    val claimDeliveryFeeDemandAmount: Long,
    val claimDeliveryFeePayMeans: String,
    val claimDeliveryFeePayMethod: String,
    val returnReceiveAddress: ReturnReceiveAddress4,
    val collectAddress: CollectAddress4,
    val collectCompletedDate: String,
    val collectDeliveryCompany: String,
    val collectDeliveryMethod: String,
    val collectStatus: String,
    val collectTrackingNumber: String,
    val etcFeeDemandAmount: Long,
    val etcFeePayMeans: String,
    val etcFeePayMethod: String,
    val holdbackDetailedReason: String,
    val holdbackReason: String,
    val holdbackStatus: String,
    val holdbackConfigDate: String,
    val holdbackConfigurer: String,
    val holdbackReleaseDate: String,
    val holdbackReleaser: String,
    val claimDeliveryFeeProductOrderIds: String,
    val claimDeliveryFeeDiscountAmount: Long,
    val remoteAreaCostChargeAmount: Long,
    val claimCompleteOperationDate: String,
    val claimRequestAdmissionDate: String,
    val collectOperationDate: String,
    val collectStartTime: String,
    val collectEndTime: String,
    val collectSlotId: String,
    val reDeliveryAddress: ReDeliveryAddress3,
    val reDeliveryMethod: String,
    val reDeliveryStatus: String,
    val reDeliveryCompany: String,
    val reDeliveryTrackingNumber: String,
    val reDeliveryOperationDate: String,
)

data class ReturnReceiveAddress4(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
    val logisticsCenterId: String,
)

data class CollectAddress4(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
)

data class ReDeliveryAddress3(
    val addressType: String,
    val baseAddress: String,
    val city: String,
    val country: String,
    val detailedAddress: String,
    val name: String,
    val state: String,
    val tel1: String,
    val tel2: String,
    val zipCode: String,
    val isRoadNameAddress: Boolean,
)

data class Delivery(
    val deliveredDate: String,
    val deliveryCompany: String,
    val deliveryMethod: String,
    val deliveryStatus: String,
    val isWrongTrackingNumber: Boolean,
    val pickupDate: String,
    val sendDate: String,
    val trackingNumber: String,
    val wrongTrackingNumberRegisteredDate: String,
    val wrongTrackingNumberType: String,
)

data class Pagination(
    val page: Long,
    val size: Long,
    val hasNext: Boolean,
)
