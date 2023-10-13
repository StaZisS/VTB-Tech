# VTB-Tech
Base:
API:

отдает описание офиса
GET("/office?{id}")
Response
единичный Json из файла offices + статистика + rating

отдает id шники office которые имеют наибольший рейтинг
GET("/office?{latitude}?{longitude}?{countOffice}")
Request
List<услуг>
{

}
Response
List<id>

отдает описание банкомата
GET(/atms?{id})
Response
единичный Json из файла atms + статистика + rating

отдает id шники atm который имеет наибольший рейтинг
GET("/office?{latitude}?{longitude}?{countAtm}")
Request
List<услуг>
{

}
Response
List<UUID>

Additional:
передаём запрос с вопросом клиента
GET("/chat-bot{query}")
возращает список Id услуг с названием услуги
Response
List<Pair<UUID,String>>

