package javazone12;

object LoginApp extends App {

  val login = new FiksLogin
  val result = login.loginWithCredentials("morten.andersen.gott", "foo")
  assert(result isRight)

}
