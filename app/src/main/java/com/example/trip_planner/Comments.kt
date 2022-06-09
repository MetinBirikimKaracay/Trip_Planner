package com.example.trip_planner

class Comments {

    var id:Int = 0
    var name:String = ""
    var surName:String = ""
    var placeName:String = ""
    var comment:String = ""
    var point:Int = 0

    constructor(name:String,surName:String,placeName:String,comment:String,point:Int){
        this.name = name
        this.surName = surName
        this.placeName = placeName
        this.comment = comment
        this.point = point
    }

    constructor(){

    }

}