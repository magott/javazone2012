import org.jsoup.Connection.Method
import org.jsoup.Jsoup
import scala.collection.JavaConverters._

class FiksLogin {
  def login(username: String, password: String) = {
    val response = Jsoup.connect("https://fiks.fotball.no/Fogisdomarklient/Login/Login.aspx")
      .data("tbAnvandarnamn", username)
      .data("tbLosenord", password)
      .data("__VIEWSTATE", "")
      .data("__EVENTVALIDATION", "")
      .data("btnLoggaIn", "Logg inn")
      .method(Method.POST)
      .followRedirects(false)
      .timeout(15000)
      .execute()

    if (response.statusCode == 302) {
      val cookie = ("myCookie",response.cookie("ASP.NET_SessionId"))
      Right(cookie)
    } else {
      Left(new RuntimeException("Login failed"));
    }
  }


  def loginWithCredentials(username:String, password:String) = {
    val loginPage = Jsoup.connect("https://fiks.fotball.no/Fogisdomarklient/Login/Login.aspx").method(Method.GET).execute()
    val loginDocument = loginPage.parse
    val viewstate = loginDocument.getElementById("__VIEWSTATE").attr("value")
    val eventvalidation = loginDocument.getElementById("__EVENTVALIDATION").attr("value")
    val sessionId = loginPage.cookie("ASP.NET_SessionId")

    val response = Jsoup.connect("https://fiks.fotball.no/Fogisdomarklient/Login/Login.aspx")
      .data("tbAnvandarnamn", username)
      .data("tbLosenord", password)
      .data("__VIEWSTATE", viewstate)
      .data("__EVENTVALIDATION", eventvalidation)
      .data("btnLoggaIn", "Logg inn")
      .method(Method.POST)
      .cookie("ASP.NET_SessionId", sessionId)
      .followRedirects(false)
      .timeout(15000)
      .execute()

    if (response.statusCode == 302) {
      val cookie = ("myCookie",sessionId)
      Right(cookie)
    } else {
      Left(new RuntimeException("Login failed"));
    }

  }

}
