package pagination

case class Page[T](
  results: List[T],
  pageNumber: Int,
  pageSize: Int,
  totalResults: Long,
  numberOfResults: Int
)

object CustomPagination {
  private val FirstPage = 1
  private val DefaultPageSize = 20

  def createPage[T](results: List[T], pageNumber: Int, pageSize: Int, totalCount: Long) = {
    Page(results, pageNumber, pageSize, totalCount, results.size)
  }
}