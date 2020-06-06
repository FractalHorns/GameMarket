package component

import data.Game
import hoc.withDisplayName
import react.*
import react.dom.*
import react.functionalComponent
import react.router.dom.navLink

interface GamePageProps : RProps {
}

fun gamePageFC(game:Game, gameIndex:Int) =
    functionalComponent<GamePageProps> {
        div("container") {
            navLink("/gamelist"){
                button(classes = "button first") {
                    +"<-"
                }
            }
            h2 { +game.name }
            p{
                +game.description
            }
            p{+"воз. рейтинг: ${game.ageRating}+"}
            h3 { +"жанр:" }
            p{ +game.genre }

            div("imgContainer") { img(src = "imgs/$gameIndex.jpg",classes = "imageC") {  } }
        }
    }

fun RBuilder.gamePage(
    game:Game,
    gameIndex: Int
) = child(
    withDisplayName("resultPage",  gamePageFC(game,gameIndex))
){}
