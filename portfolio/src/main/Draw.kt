package main

/*
기존. 함수를 만들거나 카드 드로우 작동을 할때
player.Card.add(DrawCard(deck)) 일일히 전부 적어줘야했고
리턴값이 있어 편하게 사용하기 힘듬
*/
//fun DrawCard(deck :ArrayList<Card>): Card {//셔플과 함께 사용, 첫번째 카드 뽑음
//    var dCard = Card(deck[0].name,deck[0].pattern,deck[0].value)
//    deck.removeAt(0)
//    return dCard
//}


fun drawCard(user :Deck, deck:Deck){//두번째, 함수 작성 자체는 Deck.Card.property가 되어 귀찮아졌지만 사용할때 편해짐
    user.Card.add(deck.Card[0])
    user.Scr += deck.Card[0].value
    deck.Card.removeAt(0)
//    println("카드사이즈 : "+user.Card.size)
}

fun drawStart(player: Deck, dealer :Deck, deck :Deck){
    for (i in 0..1) {//기본패
        drawCard(player,deck)
        delay(200)
        println("뽑은 카드는 " + player.Card[player.Card.size-1].pattern + player.Card[player.Card.size-1].name + "입니다" )
        drawCard(dealer,deck)
    }
    delay(200)
    showCardAndScore(player)

    delay(300)

    println()
    println(dealer.pName +" ")
    showCard(dealer.Card, 0)
    println("뒷면")
    println()
}

fun drawPlayer(player: Deck, deck: Deck) :Boolean {
    when (player.Scr) {
        in 0..20 -> {
            println(
                """추가로 드로우 하시겠습니까?
                            |1. 힛
                            |2. 스탠드
                        """.trimMargin()
            )
            when (inputCheck()) {
                1 -> {
                    drawCard(player, deck)
                    delay()
                    println("뽑은 카드는 " + player.Card[player.Card.size-1].pattern + player.Card[player.Card.size-1].name + "입니다" )
                    showCardAndScore(player)
                }
                2 -> return false
            }
        }
        21 -> {
            if (player.Card.size == 2) {
                println("블랙잭")
                player.isBJ = true
            }
            return false
        }
        else -> {//버스트
            player.Scr = -1
            return false
        }
    }
    return true
}

fun drawDealer(dealer: Deck, deck : Deck) : Boolean{
    //딜러 드로우 시키기
//    while(player.Scr != -1){//딜러 드로우 시키기
        when (dealer.Scr) {
            in 0..16 -> {
                println("딜러가 힛 합니다.")
                delay()
                drawCard(dealer, deck)
                showCardAndScore(dealer)
            }
            in 17..20 -> {
                println("딜러가 스탠드 합니다.")
                delay()
                return false
            }
            21 -> {
                println(dealer.pName +"의 점수는 21점")
                delay()
                if (dealer.Card.size == 2) {
                    println("블랙잭")
                    dealer.isBJ = true
                }
                return false
            }
            else -> {
                dealer.Scr = -1
                return false
            }
        }
//    }
    return true
}