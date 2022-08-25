package slowikps.plain

data class Credentials(val id: String, var username: String? = null, var password: String? = null) {
    override fun toString(): String {
        return "$id;$username;$password"
    }
}

fun main() {
    val cred: Pair<MutableList<Credentials>, Credentials?> =
        data.split("\n")
            .fold(Pair(mutableListOf<Credentials>(), null as Credentials?)) { (list, cred), next: String ->
                if (cred == null) {
                    list to Credentials(id = next.split("|")[1].trim())
                } else if (next.trim().length == 1) {
                    if (cred.password == null || cred.username == null) {
                        throw IllegalStateException("null")
                    }
                    list.add(cred)
                    list to null
                } else {
                    if (next.startsWith("bancard_gateway_username")) {
                        cred.username = next.substringAfter("=").replace("\\", "")
                    }
                    if (next.startsWith("bancard_gateway_password")) {
                        cred.password = next.substringAfter("=").replace("\\", "")
                    }
                    list to cred
                }
            }

    if(cred.second != null) {
        throw IllegalStateException("Boom")
    }
    println(cred.first.joinToString(separator = "\n"))
    val results = data.split("\n").chunked(6) { chunked ->
        val branchId = chunked[0].split("|")[2]
        val username = chunked.first { it.startsWith("bancard_gateway_username") }.substringAfter("=").replace("\\", "")
        val password = chunked.first { it.startsWith("bancard_gateway_password") }.substringAfter("=").replace("\\", "")


        Credentials(branchId, username, password)
    }
    println(results)
}


val data = """| 17197049055899 | #Thu Dec 19 14:03:06 UTC 2019
bancard_gateway_password=QHLFXv2vLVbS
bancard_passport_connect_public_token=public_BPR1K5Vg8KVNwsdCpLfa4F8cM3CjPNsmu7U88EwAnDQRuGSKbl3r54AXCyLpucVO
bancard_gateway_username=052060811\:salon-skanda
bancard_passport_connect_token=private_kooxulnDMjP3RP35xCclGSuwsJxG7ALUoRCPILBdmZHOOfnRulCw9ogBOebbYhAl
                      |
| 18932215844794 | #Tue Sep 04 16:00:26 UTC 2018
bancard_gateway_password=LN3JweLHO9as
bancard_gateway_username=052058996\:metrowaxx
bancard_passport_connect_token=z2t065sBCTsetzul02HmW3aB6iAztwoQPB9bhFaNP6IsT3khbTDoTMhwzSHi54pQ
bancard_passport_connect_public_token=2d93AXHblmt74xrftGiUTylEM68s3DVPocyw72HPEUaeQxZEjU1X82h5kXs3Ymvh
                                        |
| 17197049058027 | #Thu Dec 20 17:18:24 UTC 2018
bancard_gateway_password=ErIygHmKIBz1
bancard_gateway_username=052059421\:color-box
bancard_passport_connect_token=xyRbYNT9Bfkttal7rnWFIdayyISJk6XKJUJU5UxsSiPKvo3aT06WGGUMWYqLJp0Y
bancard_passport_connect_public_token=YNEzqSJGMntBjcGZJkUTfSo9iP4iQFWDl0fwTJGT2mDnnunPiPMzGpxnVTUVMswK
                                        |
| 17197049058516 | #Wed Jan 02 21:38:45 UTC 2019
bancard_gateway_password=WYlk2Z97VRk3
bancard_gateway_username=052059558\:nine-zero-one-salon-shop-901
bancard_passport_connect_token=YOJWGLhI5WODnBhG5ph0uhH70Knntou0fhSpu7s6ecdjwbCrsNTqdouIE6AnYLT5
bancard_passport_connect_public_token=k1MxRNRsPLRCIvF4TNqQAXTPFG1FAAo0oQmSzqtM0KipAAj9jS8iG9lP1pj16PES
                     |
| 17197049059218 | #Thu Aug 09 13:08:41 UTC 2018
bancard_gateway_password=n3XRpKB2G3S
bancard_gateway_username=052058860\:urban-evolution-salon
bancard_passport_connect_token=fRxzrQKEJFglQPviDdGe7Mg1RwUDJqYOkaR8Yt7XGYuyv7i9PrIy02hoW93dDKrx
bancard_passport_connect_public_token=q00nZfnNlEd6uPNQLUNgTeZlVbJk3EuvuHHZslek6N4KTkM4dOoFUF4XsIm0turm
                             |
| 17197049059219 | #Tue May 25 16:36:14 UTC 2021
bancard_gateway_password=C)j1_IuGT5J4
bancard_passport_connect_public_token=public_bR7Mp9MXlFecQeWdFrDcgmYH7fv0IltY1WjdlUgmFGA5YUlqS7gHdmC7iYVHdEfB
bancard_gateway_username=101913880\:perfect-b
bancard_passport_connect_token=private_98pYeurr6y1XWznX5ne02sinAviL6FcmplO3MXgpVNdRfGOPyWuBLlrv4s6SKvQH
                         |
| 17197049059419 | #Mon Dec 31 15:24:10 UTC 2018
bancard_gateway_password=Z8mrocUVUvc
bancard_gateway_username=052059229\:bebe-ellis-salon
bancard_passport_connect_token=lxEpsfuEZNpyf6xPhIj27nyXu8RYZ1jES90EJyfh0tXXjwjJFPSVuK8Jt4I16138
bancard_passport_connect_public_token=en1vttKxEy1kUOfqPrd8Qgrghu0c9fFIlqD7o0k1u19PF23LzmG3kf3EbpYHnZDa
                                  |
| 17197049059513 | #Thu Nov 07 16:31:44 UTC 2019
bancard_gateway_password=rEaRpxANmAw6
bancard_passport_connect_public_token=public_leQnIiUynRxAnuQeTlYqrUJM3ZlPBsR7AJS17AuO6Kh4I4x3EjjVR8LIW20zF3ve
bancard_gateway_username=052059210\:noelle-spa-for-beauty-wellness
bancard_passport_connect_token=private_fPZmBbpOX6IyyXKv69SzSER2aPzRRBRMVXRqJI9Ieb0AswAEdVknaSZWQlNBqhAr
    |
| 17214228928954 | #Wed Dec 12 21:34:36 UTC 2018
bancard_gateway_password=4L7tUz7Pf7lR
bancard_gateway_username=052059428\:dermalogica-on-montana
bancard_passport_connect_token=tU0tAodTbxPDtlYX360nCqwQpG2btUHvjfgbDTZahudxoumBiFoZbdkCe5Ii9ADG
bancard_passport_connect_public_token=l6tzRwg286o1HoTVTuaAYBz8dGHfoNcGgy3szeMknttgPyEPX6kxW3UXbwXB7D7b
                           |
| 17197049060072 | #Tue Mar 29 17:45:08 UTC 2022
bancard_gateway_password=iduf@y\=Bn2ld
bancard_passport_connect_public_token=public_TnpVrAlZZPIaufHctctPW7PL4Gpp09KnwKqQ2zqBUvbwPST9f6i3AuitgkubnKzA
bancard_gateway_username=052520062369\:the-sidedoor-salon
bancard_passport_connect_token=private_iq0o3UEQn5R8kOFSTOahMSZ7iL2SdPm1x7f4VwMCXdPO4W7VqvkwRnz9D8Gaj9wm
            |
| 17197049060190 | #Wed Jan 29 14:16:19 UTC 2020
bancard_gateway_password=wwuMz0Cm4NVv
bancard_passport_connect_public_token=public_wIxRl01gdYltysGgOt1NTOguWMfeB9IDNYJbPHigIvqc2hbI67RDQuYHuLe7kyNU
bancard_gateway_username=052060932\:tarver-hill-salon
bancard_passport_connect_token=private_xrrL3kkGmVZBWLX18Y7o1ALjWCsqjgrQ3ttOtMGHIiPTV15mMazq4HNbTEcf3cOF
                 |
| 17197049060147 | #Thu Mar 28 14:13:37 UTC 2019
bancard_gateway_password=pbdh40DnzSJ
bancard_gateway_username=052059913\:moxy-hair-studio-spa
bancard_passport_connect_token=Bixx1Klol6mD1zo34AzEMcim33Xchqdwl49Vn5N0F3wEpXi3TC9Qowry2mTgrI4b
bancard_passport_connect_public_token=3ue8Qr1YDwjGEvwcc93jcCq5tB1PgkFEiBLEPbH509aAcJ5qhntV8M9T56rpg6tC
                              |
| 17197049060277 | #Fri Apr 26 00:30:07 UTC 2019
bancard_gateway_password=p9Y9rqDDaX
bancard_gateway_username=052054607\:lontis-day-spa-and-salon
bancard_passport_connect_token=y5YgIMFBR0cVzBmymqX3muvzN2KgI1EFkvXysU3ufRGBeKnAw2s2X9xUSjOE4TBF
bancard_passport_connect_public_token=jEH6RR0nqXnRj4atEM5sHpfv76juquGXUUf5TjllaqY0t3H9CwLOqsKei92Y7ogD
                           |
| 17197049060368 | #Mon Jun 03 17:47:21 UTC 2019
bancard_gateway_password=p4X3LI9kMj
bancard_gateway_username=052060094\:body-health-and-mind-center
bancard_passport_connect_token=k3wVmOQ36iWDCdeT1yMMloeVRB7Gyzg4ReoAHg3bvAIk69ag13YjsabTyQzw0t97
bancard_passport_connect_public_token=agNvuWNGpxdMAVo2yVFj9ik3WQewzqu0DOq0ShG0hJ1xW9pkOVW3C62CI70hThQ2
                        |
| 17197049060363 | #Tue May 07 14:07:03 UTC 2019
bancard_gateway_password=Y2bqYZPAp5
bancard_gateway_username=052060002\:on-stage-1
bancard_passport_connect_token=vdfucD9zdVP6BqnE8Z9kE7ytRiNkxEWLf1s7BqCC8OuA9xxDU26XH8nRZECuNZ0L
                                                                                                                                                |
| 17197049060629 | #Fri Jul 19 14:28:58 UTC 2019
bancard_gateway_password=LfB3QarddYwI
bancard_passport_connect_public_token=public_hDFG6SGsCPA5HltaayH3W91LiYa5cGOwLRonPGNKJLvirrKp5n7ixpaRhBWS5bOU
bancard_gateway_username=052060274\:wind-willow-a-salon-company
bancard_passport_connect_token=private_8xlcBRDxmXfhSB1pw2AQUl80M0zOrhZ0oyb80wewE38J1B07EtLEow0ebuZy6ZFu
       |
| 17214228929812 | #Thu Jul 11 19:26:18 UTC 2019
bancard_gateway_password=kCiLElw1P41
bancard_passport_connect_public_token=public_TDHKue5ws4bEvnhx5TjQbnx6lo4uD09eowpNqF3V6o4FxGKUEGskynFSMLA3WewP
bancard_gateway_username=052060234\:tailor-barber-co
bancard_passport_connect_token=private_d0yJRzElD8FnHur8h3LQpW3Fx8pTn5w4mvEYbskeSpfs7GYXz7reQ63nXXB7DSRg
                   |
| 17197049060645 | #Wed Jul 17 13:49:47 UTC 2019
bancard_gateway_password=df1yRHatq4Gj
bancard_passport_connect_public_token=public_wAp0U6Lra39RoT4zD5gdW8u4iSR55rQldo29OjBvZNWfu9SCfTx2ry7csA6I55Ts
bancard_gateway_username=052060256\:sojourn-salon-and-spa
bancard_passport_connect_token=private_ckoIoMpnBDaZa7NCzAsaia8TjcqRUoSByUunmGbIhj1T0ORFsX9g25BNWRlP7Mmu
             |
| 17197049060656 | #Fri May 06 17:43:13 UTC 2022
bancard_gateway_password=VGEl${"$"}X{C18H
bancard_passport_connect_public_token=public_cAfK4tzUWjCalHXzw0GqP8QlVlnBqByAvEVwtAE05brRigCZUqT1KJn2vMxwmWut
bancard_gateway_username=052520062461\:vip-salon-and-spa
bancard_passport_connect_token=private_f0fbgwSA19o4qRyHe22gOWejISyAUHrKjVpgzr0e6pbUbWORrG8DQK7dHlBXOkeD
               |
| 17197049060689 | #Mon Jul 29 14:01:38 UTC 2019
bancard_gateway_password=9YTtHwFoZZMv
bancard_passport_connect_public_token=public_hponNvAcWnr0hSpzRP5ZQRsqY94I9miSyaSw4rapxoNxgIQcGBxXSIvJiXGMFtJL
bancard_gateway_username=052060281\:beautique-day-spa-and-salon
bancard_passport_connect_token=private_0oQjM3zxx4zyjmnlV8gBNT8egeQYK1HfbxnwjOSLSCIGrcnxRWV3WEeNTGwzMeRo
       |
| 17197049060827 | #Wed Sep 11 14:00:49 UTC 2019
bancard_gateway_password=aMglJnpZW2K
bancard_passport_connect_public_token=public_okYumf2E7GIs9ZiCtaxMMLgTJLj8ea493X4FcIwQQ8jQlvVl6fLZykgc2SqC31W0
bancard_gateway_username=052060444\:triniti-salon
bancard_passport_connect_token=private_BWL7ymBpVduFBfaBpH1iuust96FnMz3OJb54UzCp42wwiA1yzM6VOB38XF7Ec5oA
                      |
| 17248588668446 | #Tue Oct 15 18:16:18 UTC 2019
bancard_gateway_password=xWdf4yui3oR
bancard_passport_connect_public_token=public_JedB5eTYHRz94MsFixBmFCEKjnmv56mP9fOZ0lpQ7CJg3T355uxeVr1Qaf5SoMSV
bancard_gateway_username=052060616\:aveda-institute-rochester
bancard_passport_connect_token=private_grr9OEnbNKtsSgQNNgYD28ZUeXLu7yywybpzq1cs5XPHIo2OqCf3o85lJNBPaTvG
          |
| 17197049061304 | #Tue Mar 03 15:38:27 UTC 2020
bancard_gateway_password=X8vxt7c1Nj
bancard_passport_connect_public_token=public_pCxcmPKlglwkCBLkRgCzdSde7lQfCJM7aKV2D1jYG1PyYYUxUnciwPgid6kuei49
bancard_gateway_username=052060878\:dl-lowry-hairspa-boutique
bancard_passport_connect_token=private_KJscRwpFhCElPw0ETb959zX9rxiZClXgT66aCBip5JMAieOgckSCLioI6BDdxId8
           |
| 17197049061350 | #Fri Jan 03 14:34:51 UTC 2020
bancard_gateway_password=cRCFFZqIRvg4
bancard_passport_connect_public_token=public_daBWeVczpP4i7AgfAwiKy1qwQZPeTikagOTLaVI6dbRPuPrRosRakQPB6UfGPFWg
bancard_gateway_username=052060858\:the-cutting-edge-salon-spa
bancard_passport_connect_token=private_54zF4XJhjAdOiiecha8LPsGXiochbHAS53LYRGF3BFpunj2EqEBWYuOB8qbFIAwS
        |
| 17300128275998 | #Mon Dec 23 18:41:46 UTC 2019
bancard_gateway_password=NrtwaQK55G
bancard_passport_connect_public_token=public_7D1KSlqRAWJujJpXasHVXiT32ddbGFed8yvRG6S2HtrudO5cmraF8pycuwCj0LMB
bancard_gateway_username=052060827\:serenity-couture-rochester
bancard_passport_connect_token=private_dQSmb9BujlS5wbJJZNJu9Y0vQNoClwK4Rlvga5RiJwGSQBj3rf4S4tyPOyhuDYEJ
          |
| 17282948406814 | #Fri Dec 20 20:21:19 UTC 2019
bancard_gateway_password=MLRpqIn3Io
bancard_passport_connect_public_token=public_2SEoED8viwgjeJClHKCWUdRMZ2B5YKeUFck8sfaDaBHZXMtfp836BH2jqRmBQy8A
bancard_gateway_username=052060826\:aveda-institute-des-moines
bancard_passport_connect_token=private_Xj4gDpZszXb1rcYGtq3k3B9VOz0b3PeTwd6fH4yOPxIw0yxtDTWGdhh9XWg4udbm
          |
| 17214228930538 | #Mon Feb 24 15:18:34 UTC 2020
bancard_gateway_password=syqbL0ivMz
bancard_passport_connect_public_token=public_wpSQyuTOJJcVnLmPmNhR35m4OaiQYauSxo3o8kqBn1iy0hlnGf3eoxSlWvWceGyF
bancard_gateway_username=052060888\:deep-roots-atx-salon-360
bancard_passport_connect_token=private_QzEqdTeRkshHINwOaawT5Z3IY8OiFpNT2sPYSwFGGeMLqBvODNIUhr1eAJDNUwkY
            |
| 17197049061359 | #Mon Jan 27 15:12:06 UTC 2020
bancard_gateway_password=yAqyYXVvWE8
bancard_passport_connect_public_token=public_7cm3iZCt3V443lbZzZIorOW3zJqaLDbVm7MoQrZpZxuelNSH7G1XyP02YX1ZHvnL
bancard_gateway_username=052060921\:the-beauty-parlour
bancard_passport_connect_token=private_pi7hosKAOi6RUUbWf0XyDYULQPm5Pzf2KeG0MFYy2LwX00yYlpC9fdz1gBUAQ6JA
                 |
| 17197049061595 | #Tue Mar 17 18:12:34 UTC 2020
bancard_gateway_password=KT3cQJD4Py2
bancard_passport_connect_public_token=public_plbJLp6X0Dsjbmo6QGKWd6uSxOM4KarrKOR4pY45NkKGeQA2mLLshvwuYQyNlQ9O
bancard_gateway_username=052061064\:honeybee-hair-parlor
bancard_passport_connect_token=private_CN8mPGLFgIsiLlZISq8lQfuYUTMp9tDBxI1NiiRlUNfNIspwPP32dchfmp1W2BSC
               |
| 17197049061692 | #Wed Apr 08 20:00:25 UTC 2020
bancard_gateway_password=JgaCj2DOhSh
bancard_passport_connect_public_token=public_bkxAkiVmYZ1MmvoDBye9QfJ73yT9RM75gtORQAzOSZ4gn0bQyl3daFteQbdjjGiY
bancard_gateway_username=052061124\:laboratorie
bancard_passport_connect_token=private_s0htYFyqEm13pLKTl67EWAEdTyolkm7ZzWHlosl0Hgz2BXBnlrWbrxyS7gkjjfyZ
                        |
| 17197049061723 | #Tue Apr 14 18:56:05 UTC 2020
bancard_gateway_password=Mu7gF6bF8W
bancard_passport_connect_public_token=public_JqQYKqtQiPWCe14pJN1SpRwQXRx9ZjRk8NjyjEkDUyfm0p2EfDP8BNRMq15UxuVX
bancard_gateway_username=052061141\:meraki-salon-and-spa
bancard_passport_connect_token=private_py38zDZBwmaCJ7HCMgTn2c8aCg0YIo1PGoZtgdY2p5I2WDUUEYN6oKOHfamoIz7o
                |
| 17248588667746 | #Fri Aug 28 20:21:54 UTC 2020
bancard_gateway_password=t419VhbDA8su
bancard_passport_connect_public_token=public_CET62jOcp2VUsrFYKMJCLhG69eapFYzGnMIm5GNZtsaUsCxHHGBlOdLXv8QZK053
bancard_gateway_username=052060893\:lus-barber-shop-pearland
bancard_passport_connect_token=private_I2SH3Du20Uk0xLh6bGmuJcoxT7qYvATphTg30ggkMVqY4anXQC118Olc9COlO3UT
          |
| 17214228931979 | #Mon Feb 01 14:52:17 UTC 2021
bancard_gateway_password=dPhsq48PGR
bancard_passport_connect_public_token=public_MfJUFJAEak5kmIrfwG0XFlNs5mHIKm8izVHaK69sMgMC3TZR0nRowa3N3bQewuS1
bancard_gateway_username=100882888\:salon-council
bancard_passport_connect_token=private_0SwvdjpYkJgcDqIbcKrfThI5qBiY47foMVS9zsN3q76Q0ZHdjrTVYuJE6ua4rXcd
                       |
| 17197049063906 | #Tue Nov 02 15:55:12 UTC 2021
bancard_gateway_password=b)pgw${"$"}jpQ2Ci
bancard_passport_connect_public_token=public_w4YAhWPambvjcfC0MWUv6jo4cmoZhwRn371DHpeEHb0t5DxOQUUOiXM8Fc9HBJ1S
bancard_gateway_username=052520061986\:amp-studios
bancard_passport_connect_token=private_nOMWdKgLBlvkgvGWo8NwoTV5pIQ32CsQZXdlH0euUV36G5KB4GBNSKDcJJKhvPE0
                    |
| 17197049064038 | #Tue Nov 30 15:17:39 UTC 2021
bancard_gateway_password=f)(D1LTqbrL
bancard_passport_connect_public_token=public_FU4BaTwcyNeKUDyNjvDrFpbNzLOSUUN3aDSuOIZJQNEuYvLO0zZ8xl5DX1IlnNt6
bancard_gateway_username=052520062045\:hair-solutions
bancard_passport_connect_token=private_ixgf7YJsb7ZHptxuwDwRs7XKS2yx29t6W6FmYkkJdlnhzfkVPJz7PQiEHBROzWGu
                  |
| 17197049064520 | #Wed Jan 26 15:46:56 UTC 2022
bancard_gateway_password=Q}h0SarE)CR6
bancard_passport_connect_public_token=public_Tf5Vnyt8LUQb0EU0nzfhgfXjNfQeCurC12ET12Baf6NrFHwaozRpd2EjsxPCeXxR
bancard_gateway_username=052520062178\:shea-madison-salon
bancard_passport_connect_token=private_WflgzBxhUgKCGaht4F1TZVILgCcOH8xaHFIZAoxp5RstZcoJXiVH0SBO2dPtBAZb
             |
| 17197049064600 | #Tue Feb 08 14:08:25 UTC 2022
bancard_gateway_password=3${"$"}eel78I\#5SG
bancard_passport_connect_public_token=public_rF3OkzRFFEugYYZzfHjpJnGG0qnnEcccTqJeYwTGbK9JBeoa0ndsCeXVLt3zTiVH
bancard_gateway_username=052520062224\:the-j-house-spa
bancard_passport_connect_token=private_X4QHmLlrWPutMrXdqfikTqaBbu63pwPiSapbAaAzmkqkTBfAqGUC14tOc8HE5TY0
               |
| 17265768534165 | #Wed Mar 02 17:07:32 UTC 2022
bancard_gateway_password=0iN\=G8WG\#9WE
bancard_passport_connect_public_token=public_GcCJkEVCwETHTtOS8eMMaJ8Zu47B1UNnmQs7z3CmwlnPuMMx3vRFjq1sfH7geGOb
bancard_gateway_username=052520062280\:salon-718
bancard_passport_connect_token=private_ZLMSj9wW7C1iTgRNzSB0Vw724ycsze1E5BBdtoDdNUmKJFk32mUL9ZZJSfD7t4X4
                    |
| 17197049064659 | #Tue Feb 01 14:51:16 UTC 2022
bancard_gateway_password=d5)maIr\#qF
bancard_passport_connect_public_token=public_f29n1rNXAh89hsTJSQuyl2n1PHc9A77OB7o94eM3PVp6O17aWTFl4SimzuT5Ajxk
bancard_gateway_username=052520062201\:noli-salon
bancard_passport_connect_token=private_pH07jaifz0q0JrZ2EEznPXFeZW9bOfPu0AjzpAWuKyHGnXvACK3efcNmQuyQC691
                      |
| 17197049064853 | #Wed Apr 20 13:20:14 UTC 2022
bancard_gateway_password=Fg1\=(E0YLHc
bancard_passport_connect_public_token=public_rtqa93Jsoy1HTvWJ4cx9aVmPXjO2rXESSYmqpByM3niE6VsjiFxWEICkCRAmnUuK
bancard_gateway_username=052520062435\:stylist-at-north
bancard_passport_connect_token=private_JE5pZ8OPe93Amkw7RZ9FniCHzZkJhIA9XIhNM5bBvzQtetbPsPCG0CcIXFT09bR4
               |
| 17197049064878 | #Fri Mar 25 17:12:45 UTC 2022
bancard_gateway_password=pf(sB\#5scHl4
bancard_passport_connect_public_token=public_zpaPC9J4L62oaMnXLQi5haQ9Phbi5Ze5BNyG9RLKiaLj2jIuTB7DgCY0QJEahYva
bancard_gateway_username=052520062364\:elle-b-salon-central-location
bancard_passport_connect_token=private_OObnVu6PWXcJ6fItHpDHWmP8UFoHOvln2V4VrhUcUaTEMRqUObfUnYpz7gUKa9m0
 |
| 17197049064936 | #Tue Mar 29 15:58:12 UTC 2022
bancard_gateway_password=n%qkRO81@cQ
bancard_passport_connect_public_token=public_n1jr7xL9GHxmOH9ODgfRa8IsNjLlcRaPhhxxoITsevefxR1HNmUfpQf7uWEw3GAB
bancard_gateway_username=052520062370\:bellus-wax-studio
bancard_passport_connect_token=private_7B1N0d0sI36eAOZC8iRvnR1zwLWXEKHGuitYJ8uFnDwCnds9BLTWc5DJqGAzzAEA
               |    """.trimIndent()
