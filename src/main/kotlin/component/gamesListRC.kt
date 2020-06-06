package component

import react.*
import react.dom.*
import data.*
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import react.router.dom.navLink
import redux.FilterByGenre
import redux.FilterByRating
import redux.FilterByYearCreate
import redux.NoFilter
import store
import kotlin.browser.document

interface GamesListFullProps : RProps {
    var testNum: Int
}

fun gamesListRC(games:MutableList<Game>) =
    functionalComponent<GamesListFullProps> {
	    div("container"){
			span{ +"фильтр по:" }
			select {
				attrs.id = "filterSelect"
				option { +"не выбрано" }
				option { +"жанр" }
				option { +"год выпуска" }
				option { +"воз. рейтинг" }
			}
			input(InputType.text) {
				attrs.id = "filterText"
			}
			button(classes = "button first") {
				+"filt"
				attrs.onClickFunction = {
					val asd = (document.getElementById("filterSelect") as HTMLSelectElement).value
					val dsa = (document.getElementById("filterText") as HTMLInputElement).value
					console.log("asd = $asd")
					when(asd){
						"none" -> store.dispatch(NoFilter())
						"жанр" -> store.dispatch(FilterByGenre(dsa))
						"год выпуска" -> store.dispatch(FilterByYearCreate(dsa.toInt()))
						"воз. рейтинг" ->store.dispatch(FilterByRating(dsa))
						else -> store
					}
				}
			}
			th { +"#" }
			th { +"название" }
			th { +"жанр" }
			th { +"год выпуска" }
			th { +"воз. рейтинг" }
			th { +"инфо" }
			games.mapIndexed{gameIndex, game ->
				if(game.isVisible){
					tr {
						td { +"$gameIndex" }
						td { +game.name }
						td { +game.genre}
						td { +"${game.dateOfCreation}" }
						td { +"${game.ageRating}+" }
						td { navLink("/game$gameIndex") { +"MORE" } }
					}
					}
				}
			}
	    }


fun RBuilder.gamesListRC(
	games:MutableList<Game>,
	testNum: Int
) = child(withDisplayName("gamesListRC", gamesListRC(games))) {
        attrs.testNum = testNum
    }
