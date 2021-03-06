package com.ciandt.estagio2020.oitavaaula.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person (
    @ColumnInfo var firstName: String,
    @ColumnInfo var lastName: String,
    @ColumnInfo var age: Int,
    @PrimaryKey(autoGenerate = true) val idPerson: Int = 0
) {
    override fun toString(): String {
        return firstName + " " + lastName + ", " + age
    }
}















/*
"Elvina","Lovings",
"Mckenzie","Speece",
"Marline","Hammaker",
"Allan","Ferro",
"Penney","Marrow",
"Debroah","Desroches",
"Pattie","Tippin",
"Hipolito","Wittig",
"Raquel","Marksberry",
"Elisha","Liddle",
"Anya","Stegner",
"Chelsie","Whittier",
"Amee","Oliveros",
"Shantelle","Sessoms",
"Andreas","Eubank",
"Jessi","Crinklaw",
"Stuart","Whited",
"Valery","Dalpiaz",
"Desiree","Brashears",
"Kala","Schulz",
"Beryl","Hafer",
"Willow","Dowler",
"Nelle","Leto",
"Roseanna","Fader",
"Edie","Cogdill",
"Alita","Schober",
"Artie","Dewald",
"Susann","Nygren",
"Anjelica","Waugh",
"Shantay","Buss",
"Pa","Bonifacio",
"Rossana","Lopiccolo",
"Idalia","Barrentine",
"Caroyln","Ruf",
"Janett","Kostelnik",
"Edith","Summa",
"Gisele","Rizzuto",
"Song","Prowse",
"Lucretia","Hensley",
"Elvie","Mallory",
"Ora","Thelen",
"Gabrielle","Sak",
"Myrta","Cordova",
"Stasia","Massa",
"Donovan","Riggs",
"Dot","Baynes",
"Chuck","Lona",
"Stefanie","Dauer",
"Melita","Gebo",
"Torri","Griffie
"Hui","Mashburn",
"Nigel","Wilmer",
"Sena","Rosenbloom",
"Elodia","Haase",
"Tennille","Giebler",
"Jeromy","Goodall",
"Carolee","Snay",
"Tillie","Ridenhour",
"Danielle","Todd",
"Jazmine","Wooton",
"Shanika","Veitch",
"Autumn","Humber",
"China","Uhlman",
"Lacy","Gundlach",
"Stephany","Foutz",
"Malorie","Zielke",
"Misty","Meiners",
"Pearlene","Rappaport",
"Darby","Volpe",
"Sonia","Bresler",
"Elza","Kernan",
"Marie","Drews",
"Horace","Lund",
"Avril","Goldsby",
"Earle","Soja",
"Windy","Kane",
"Consuelo","Godoy",
"Oliver","Mondy",
"Delmy","Teer",
"Reina","Chenard",
"Natasha","Klann",
"Sherise","Rahman",
"Lois","Marin",
"Pamula","Vine",
"Carla","Lai",
"Bong","Schacht",
"Cheyenne","Popa",
"Lindsey","Greenhouse",
"Parthenia","Sarcone",
"Elfrieda","Toups",
"Lisandra","Gast",
"Levi","Ritz",
"Domenic","Royals",
"Jaymie","Malcomb",
"Rivka","Stapler",
"Shenna","Brodsky",
"Aubrey","Treaster",
"Dusti","Buxton",
"Erik","Mulhern",
"Deandra","Oliva",


 */
