package groovyx.gaelyk.remote

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.google.appengine.api.utils.SystemProperty
import com.google.appengine.api.datastore.DatastoreServiceFactory

class RemoteControlServlet extends groovyx.remote.transport.http.RemoteControlServlet {
	@Override
	protected boolean validateRequest(HttpServletRequest request, HttpServletResponse response) {
		boolean valid = super.validateRequest(request, response)
		if (valid && SystemProperty.environment.value() != SystemProperty.Environment.Value.Development) {
			response.status = 403
			valid = false
		}
		valid
	}

	@Override
	protected groovyx.remote.server.Receiver createReceiver() {
		new Receiver(datastore: DatastoreServiceFactory.datastoreService)
	}
}
