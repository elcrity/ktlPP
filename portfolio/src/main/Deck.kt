package main

class Deck(
    var pName: String,//덱을 가진 플레이어 이름
    var Budjet :Int = 100,
    var Scr : Int = 0,
    var Card : ArrayList<Card> = ArrayList(),//플레이어가 가지고 있는 덱
    var isBJ : Boolean = false
)

fun makePlayer(name: String, cost: Int = 100): Deck = Deck(name,cost)//플레이어 이름 + Card형태의(name,pattern,value값) 배열 생성

fun setDeck() : Deck{//정해진 벌수의 카드로 패산 생성
    println("몇 벌의 카드로 게임을 시작할까요?")
    val num = inputCheck()

    val deck = Deck("덱")

    for(i in 0 until 52*num){
        val card = Card()
        when(i%13){//((0~12)+13*i)까지 각각 A~K
            0 -> card.name = "A"
            in 1..9 ->  card.name = ((i%13)+1).toString()
            10 -> card.name = "J"
            11 -> card.name = "Q"
            12 -> card.name = "K"
        }
        when(i%52/13){//13장마다 모양 변경, 카드 한벌 52장마다 초기화
            0 -> card.pattern = "♠"
            1 -> card.pattern = "◆"
            2 -> card.pattern = "♥"
            3 -> card.pattern = "♣"
        }
        when(i%13){//1~10까지 카드 값 부여
            in 0..9 -> card.value = (i%13)+1
            in 10..12 -> card.value = 10
        }
/*        when(i%13){//카드 갯수, 드로우 확인용
            in 0..9 -> card.value = i
            in 10..12 -> card.value = i
        }*/

        deck.Card.add(card)
    }
//덱 정상 생성 확인용
//    println("현재 카드 덱의 숫자는 " + deck.Card.size +"입니다.")
    return deck
}

fun resetDeck(user:Deck, deal:Deck ,base:Deck){
    for(i in 0 until user.Card.size){
        base.Card.add(user.Card[0])
        user.Card.removeAt(0)
    }
    for(i in 0 until deal.Card.size){
        base.Card.add(deal.Card[0])
        deal.Card.removeAt(0)
    }
    user.Scr = 0
    deal.Scr = 0
}