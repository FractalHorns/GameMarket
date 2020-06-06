package component

import data.Game
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*
import react.functionalComponent
import kotlin.browser.document

interface searchProps : RProps {
    var games: MutableList<Game>
}

fun searchtFC() =
    functionalComponent<searchProps> { props ->
        div("container search"){
            h1{+"Поиск"}
            var zxc:Int = -1
            input(InputType.text){
                attrs.id = "inputSearch"
            }

            button {
                +"ВВОД"
                attrs.id = "searchButton"
                attrs.onClickFunction = {
                    val ddd = (document.getElementById("inputSearch") as HTMLInputElement).value
                    props.games.forEachIndexed { i,j ->
                        if(j.name == ddd){
                            zxc = i
                            render(document.getElementById("cnt")){
                                a("#/game$i"){
                                    button {
                                        +j.name
                                    }
                                }
                            }
                        }
                    }
                    console.log("zxc = $zxc")
                    if(zxc==-1){
                        render(document.getElementById("cnt")){
                            +"не найдено"
                        }
                    }
                }
            }
            div {
                attrs.id = "cnt"
            }
        }
    }

fun RBuilder.search(
    game: MutableList<Game>
) = child(withDisplayName("search", searchtFC())){
    attrs.games = game
}

