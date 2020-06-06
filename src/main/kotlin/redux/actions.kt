package redux

class FilterByGenre(val genre:String) : RAction

class FilterByYearCreate(val year:Int) : RAction

class FilterByRating(val rate:String) : RAction

class NoFilter() : RAction