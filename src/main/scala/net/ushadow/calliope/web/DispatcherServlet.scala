package net.ushadow.calliope.web

import javax.servlet.http._
import net.ushadow.calliope._
import com.google.appengine.api.datastore._

class DispatcherServlet extends HttpServlet {

  private val datastore = DatastoreServiceFactory.getDatastoreService
  private val dispatcher = new Dispatcher

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    dispatcher.dispatch(toStorableEvent(req))
  }

  private def toStorableEvent(req: HttpServletRequest): Event = {
    val keyAsString = req.getParameter("key")
    val key = KeyFactory.stringToKey(keyAsString)
    val entity = datastore.get(key)
    return new StorableEvent(entity)
  }

}