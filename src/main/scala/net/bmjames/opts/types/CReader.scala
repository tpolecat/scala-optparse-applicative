package net.bmjames.opts.types

import cats._, cats.data._, cats.implicits._
import cats._, cats.data._, cats.implicits._

// As we don't have the completion functionality, this is serving as a useless wrapper
final case class CReader[A](reader: ReadM[A])

object CReader {

  implicit val cReaderFunctor: Functor[CReader] =
    new Functor[CReader] {
      def map[A, B](fa: CReader[A])(f: A => B): CReader[B] =
        CReader(fa.reader.map(f))
    }

}
