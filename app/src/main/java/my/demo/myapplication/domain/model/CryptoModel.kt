package my.demo.myapplication.domain.model

/**
 * Created by Shah on 17-11-2023.
 */
data class CryptoModel(
    var name:String,
    var symbol:String,
    var slug:String,
    var price:Double,
    var marketCap:Double,
    var percentChange24h:Double,
    var volume24h:Double,
    var isPositive:Boolean,
    var logo:String
)
