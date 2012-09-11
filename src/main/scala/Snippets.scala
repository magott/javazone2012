package javazone12;

import xml.NodeSeq

object Snippets {

  def welcomePage = emptyPage(
    <h1>Hello</h1> <h2>Is it me you're looking for?</h2>
  )

  def emptyPage(body: NodeSeq) = {
    <html>
      <head>
        <title>My page</title>
      </head>
      <body>
        {body}
      </body>
    </html>
  }

}
