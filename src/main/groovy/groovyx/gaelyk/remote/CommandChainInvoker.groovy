package groovyx.gaelyk.remote

import groovyx.remote.CommandChain
import groovyx.remote.Command

class CommandChainInvoker extends groovyx.remote.server.CommandChainInvoker {
	CommandChainInvoker(ClassLoader parentLoader, CommandChain commandChain) {
		super(parentLoader, commandChain)
	}

	@Override
	protected createInvoker(ClassLoader loader, Command command) {
		return new CommandInvoker(loader, command)
	}
}
