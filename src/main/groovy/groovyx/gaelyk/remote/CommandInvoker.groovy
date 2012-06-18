package groovyx.gaelyk.remote

import groovyx.remote.Command
import groovyx.remote.Result
import groovyx.gaelyk.GaelykCategory

class CommandInvoker extends groovyx.remote.server.CommandInvoker{

	final ClassLoader parentLoader
	final Command command
	final Class rootClass
	final List supportClasses

	CommandInvoker(ClassLoader parentLoader, Command command) {
		super(parentLoader, command)
	}

	@Override
	protected Result resultForThrown(Throwable thrown) {
		Result.forThrown(thrown)
	}



	protected invoke(instance, arg = null) {
		use(GaelykCategory) {
			super.invoke(instance, arg)
		}
	}
}
