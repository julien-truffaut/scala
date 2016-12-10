class Foo[A] {
  def compose(a: Foo[A]): Foo[A] = new Foo[A]
  def compose(b: Bar[A]): Bar[A] = new Bar[A]

  // non overloaded method
  def composeBar(b: Bar[A]): Bar[A] = new Bar[A]
}

class Bar[A] {
  def compose(a: Foo[A]): Bar[A] = new Bar[A]
  def compose(b: Bar[A]): Bar[A] = new Bar[A]
}

object Test {
  val fooS = new Foo[String]
  val barS = new Bar[String]
  def barA[A] = new Bar[A]

  // foos and bars compose together
  fooS compose fooS
  barS compose barS
  fooS compose barS
  barS compose fooS


  fooS compose barA

  // but it works if you add a type annotation or a non overloaded method
  fooS compose barA[String]
  fooS composeBar barA
}