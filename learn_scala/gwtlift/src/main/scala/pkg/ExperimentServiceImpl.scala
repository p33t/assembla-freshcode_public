package pkg

import com.google.gwt.user.server.rpc.RemoteServiceServlet
import biz.freshcode.learn.gwtlift.client.ExperimentService
import biz.freshcode.learn.gwtlift.shared.AnObject
import java.lang.String
import java.util.Date

class ExperimentServiceImpl extends RemoteServiceServlet with ExperimentService {
  def sendObject(obj: AnObject): String = "Received at " + new Date() + " " + obj.str + " " + obj.num
}