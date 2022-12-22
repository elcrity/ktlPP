package main

class Card(
    var name :String = "미정",
    var pattern :String = "모름", //스페이드, 다이아, 하트, 클로버
    var value : Int = -1
)


fun showCard(Card:ArrayList<Card>, i:Int){
    print(Card[i].pattern)
    print(Card[i].name +" ")
//    print(" " + Card[i].value +" ")
}

fun showHandCard(deck:Deck){
    println(deck.pName + "의 현재 패는 ")
    for(i in 0 until deck.Card.size) {
        print(deck.Card[i].pattern + " " + deck.Card[i].name)
        when(i) {
            deck.Card.size-1 -> continue
            else -> print(" / ")
        }
    }
    println()
}

fun yesAce(user: Deck){
    for(i in 0 until user.Card.size){
        if(user.Card[i].value == 1){
            println(
                """${user.Card[i].pattern}${user.Card[i].name}를 11로 계산하시겠습니까?
                        |1. 예
                        |2. 아니요
                    """.trimMargin()
            )
            when(inputCheck()){
                1 -> {
                    user.Card[i].value = 11
                    user.Scr += 10
                    showCardScore(user)
                }
            }
        }
    }
}

fun dealerAce(dealer: Deck, player:Deck){
    println("딜러가 고민중입니다.")
    delay()
    for(i in 0 until dealer.Card.size){
        when (dealer.Card[i].value) {
            1 -> when{
                    dealer.Scr < player.Scr -> {
                        if(dealer.Scr + 10 > player.Scr && dealer.Scr +10 < 22) {
                            dealer.Card[i].value = 11
                            dealer.Scr += 10
                            println("딜러가 " + dealer.Card[i].pattern + dealer.Card[i].name + "카드를 11점으로 계산하기 시작합니다.")
                            showScore(dealer)
                        }else break
                    }
                    dealer.Scr > player.Scr -> {
                        break
                    }
                }
            11 -> when{
                dealer.Scr > 21 -> {
                    if(dealer.Scr - 10 < 21){
                        dealer.Card[i].value = 1
                        dealer.Scr -= 10
                        println("딜러가 " + dealer.Card[i].pattern + dealer.Card[i].name + "카드를 1점으로 계산하기 시작합니다.")
                        showScore(dealer)
                    }else break
                }
            }
            else -> continue
        }
    }
}