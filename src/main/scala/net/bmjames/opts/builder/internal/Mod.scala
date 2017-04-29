package net.bmjames.opts.builder.internal

import net.bmjames.opts.types.OptProperties

import cats._, cats.data._, cats.implicits._
import cats._, cats.data._, cats.implicits._

/** An option modifier.
  */
case class Mod[F[_], A](f: F[A] => F[A], prop: DefaultProp[A], g: OptProperties => OptProperties) {

  def <>(that: Mod[F, A]): Mod[F, A] =
    this |+| that
}

object Mod {

  def option[F[_], A](f: OptProperties => OptProperties): Mod[F, A] =
    Mod(identity, Monoid[DefaultProp[A]].empty, f)

  def field[F[_], A](f: F[A] => F[A]): Mod[F, A] =
    Mod(f, Monoid[DefaultProp[A]].empty, identity)

  implicit def modMonoid[F[_], A]: Monoid[Mod[F, A]] =
    new Monoid[Mod[F, A]] {
      def empty: Mod[F, A] =
        Mod(identity, Monoid[DefaultProp[A]].empty, identity)

      // def append(f1: Mod[F, A], f2: => Mod[F, A]): Mod[F, A] =
      //   Mod(f2.f compose f1.f, f2.prop |+| f1.prop, f2.g compose f1.g)
    }
}
