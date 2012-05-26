package groovyx.gaelyk.remote

import groovyx.remote.CommandChain
import groovyx.remote.Result

class CommandChainInvoker {
	final ClassLoader parentLoader
	final CommandChain commandChain

	private CommandChainInvoker(ClassLoader parentLoader, CommandChain commandChain) {
		this.parentLoader = parentLoader
		this.commandChain = commandChain
	}

	Result invokeAgainst(delegate, firstArg = null) {
		def arg = firstArg
		def lastResult = null
		def lastCommand = commandChain.commands.last()

		for (command in commandChain.commands) {
			lastResult = new CommandInvoker(parentLoader, command).invokeAgainst(delegate, arg)

			if (command != lastCommand) {
				if (lastResult.thrown) {
					return lastResult
				} else if (lastResult.wasUnserializable) {
					// unserializable is ok when chaining
					arg = lastResult.unserializable
				} else {
					arg = lastResult.value
				}
			}
		}

		lastResult
	}
}
