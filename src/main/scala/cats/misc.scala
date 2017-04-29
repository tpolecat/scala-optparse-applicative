package cats

import cats.implicits._

object misc {

  implicit class MoreOptioOps[A](fa: Option[A]) {
    def orEmptyK[F[_]](implicit ev: Alternative[F]): F[A] =
      fa.fold(ev.empty[A])(ev.pure(_))
  }

  implicit class MoreApplyOps[F[_]: Apply, A](fa: F[A]) {
    def <*>[B](fab: F[A => B]): F[B] = fab ap fa
  }

}
