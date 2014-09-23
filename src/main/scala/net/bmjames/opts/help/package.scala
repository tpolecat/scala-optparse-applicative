package net.bmjames.opts

import net.bmjames.opts.types._
import net.bmjames.opts.common.showOption

import scalaz.syntax.std.list._
import scalaz.syntax.functor._

package object help {

  import Pretty._

  /** Style for rendering an option. */
  final case class OptDescStyle(sep: Doc, hidden: Boolean, surround: Boolean)

  /** Generate description for a single option. */
  def optDesc[A](pprefs: ParserPrefs, style: OptDescStyle, info: OptHelpInfo, opt: Opt[A]): Chunk[Doc] = {
    val ns = opt.main.names
    val mv = Chunk.fromString(opt.props.metaVar)
    val descs = ns.sorted.map(string _ compose showOption)
    val desc = Chunk.fromList(descs.intersperse(style.sep)) <<+>> mv
    val vis = opt.props.visibility
    val showOpt = if (vis == Hidden) style.hidden else vis == Visible
    val suffix: Chunk[Doc] = if (info.multi) Chunk.fromString(pprefs.multiSuffix) else Chunk.zero

    def render(chunk: Chunk[Doc]): Chunk[Doc] =
      if (! showOpt) Chunk.zero
      else if (chunk.isEmpty || ! style.surround) chunk <> suffix
      else if (info.default) chunk.map(brackets) <> suffix
      else if (descs.drop(1).isEmpty) chunk <> suffix
      else chunk.map(parens) <> suffix

    render(desc)
  }

  /** Generate descriptions for commands. */
  def cmdDesc[A](p: Parser[A]): Chunk[Doc] =
    ???

}