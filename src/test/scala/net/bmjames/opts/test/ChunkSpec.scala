package net.bmjames.opts.test

import net.bmjames.opts.helpdoc.Chunk
import net.bmjames.opts.internal._
import net.bmjames.opts.types.Doc

import cats._, cats.data._, cats.implicits._
import cats._, cats.data._, cats.implicits._
import cats._, cats.data._, cats.implicits._
import cats._, cats.data._, cats.implicits._
import org.scalacheck.{Arbitrary, Gen, Properties}
import org.scalacheck.Prop.forAll

import Arbitrary._, Gen._

object ChunkSpec extends Properties("Chunk") {

  implicit def arbitraryChunk[A](implicit ev: Arbitrary[Option[A]]): Arbitrary[Chunk[A]] =
    Arbitrary(ev.arbitrary.map(Chunk(_)))

  def equalDocs(w: Int, d1: Doc, d2: Doc): Boolean =
    Doc.prettyRender(w, d1) == Doc.prettyRender(w, d2)

  property("fromList 1") = forAll { xs: List[String] => Chunk.fromList(xs).isEmpty == xs.isEmpty }

  property("fromList 2") = forAll { xs: List[String] => Chunk.fromList(xs) == xs.map(_.pure[Chunk]).combineAll }

  property("extract 1") = forAll { s: String => Chunk.extract(s.pure[Chunk]) == s }

  property("extract 2") = forAll { x: Chunk[String] => Chunk.extract(x.map(_.pure[Chunk])) == x }

  property("fromString 1") = forAll(posNum[Int], arbitrary[String]) { (w, s) =>
    equalDocs(w, Chunk.extract(Chunk.fromString(s)), Doc.string(s))
  }

  property("fromString 2") = forAll { s: String => Chunk.fromString(s).isEmpty == s.isEmpty }

  property("paragraph") = forAll { s: String => Chunk.paragraph(s).isEmpty == words(s).isEmpty }
}
