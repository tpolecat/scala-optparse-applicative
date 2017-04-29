package net.bmjames.opts.builder.internal

import cats._, cats.data._, cats.implicits._
import cats._, cats.data._, cats.implicits._
import cats._, cats.data._, cats.implicits._

case class DefaultProp[A](default: Option[A], sDef: Option[A => String])

object DefaultProp {

  implicit def defaultPropMonoid[A]: Monoid[DefaultProp[A]] =
    new Monoid[DefaultProp[A]] {
      def zero: DefaultProp[A] =
        DefaultProp(None, None)

      def append(f1: DefaultProp[A], f2: => DefaultProp[A]): DefaultProp[A] =
        DefaultProp(f1.default <+> f2.default, f1.sDef <+> f2.sDef)
    }
}
