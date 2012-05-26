package groovyx.gaelyk.remote

import groovyx.remote.CommandChain

class Receiver extends groovyx.remote.server.Receiver {
	Receiver(ClassLoader classLoader, Map contextStorageSeed) {
		super(classLoader, contextStorageSeed)
	}

	protected createInvoker(ClassLoader classLoader, CommandChain commandChain) {
		new CommandChainInvoker(classLoader, commandChain)
	}
}
