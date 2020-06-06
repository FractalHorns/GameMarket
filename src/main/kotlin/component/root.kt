package component

import data.*
import hoc.withDisplayName
import react.*
import react.dom.*
import react.router.dom.*
import redux.*
import react.RBuilder
import store

interface RootProps : RProps {
    var store: Store<State, RAction, WrapperAction>
}
fun rootFC() =
    functionalComponent<RootProps> {
        ul{
            li { navLink("/gamelist") { +"game list" } }
            li { navLink("/search") { +"search" } }
        }
        switch{
            store.getState().games.mapIndexed { gameIndex, game ->
                route("/game$gameIndex",
                        exact = true,
                        render = {
                            gamePage(game, gameIndex)
                        }
                )
            }
            route("/gamelist",
                    exact = true,
                    render = {
                        gamesListRC(store.getState().games, 5)
                    }
            )
            route("/search",
                    exact = true,
                    render = {
                        search(store.getState().games)
                    }
            )
        }
    }

fun RBuilder.root(store: Store<State, RAction, WrapperAction>) =
    child(withDisplayName("Root", rootFC())){
        attrs.store = store
    }






