package groovyx.gaelyk.remote

import groovyx.remote.Command
import groovyx.remote.Result
import org.codehaus.groovy.runtime.InvokerInvocationException
import groovyx.remote.util.ClassLoaderConfigurableObjectInputStream
import groovyx.gaelyk.GaelykCategory

class CommandInvoker {

	final ClassLoader parentLoader
	final Command command
	final Class rootClass
	final List supportClasses

	private CommandInvoker(ClassLoader parentLoader, Command command) {
		this.parentLoader = parentLoader
		this.command = command
	}

	Result invokeAgainst(delegate, arg = null) {
		try {
			def instance = instantiate()
			instance.resolveStrategy = Closure.DELEGATE_ONLY
			instance.delegate = delegate
			Result.forValue(invoke(instance, arg))
		} catch (Throwable thrown) {
			// If the server and client do not share the groov classes, we get this
			if (parentLoader.loadClass(InvokerInvocationException.name).isAssignableFrom(thrown.class)) {
				thrown = thrown.cause
			}

			Result.forThrown(thrown)
		}
	}

	def instantiate() {
		def classLoader = new GroovyClassLoader(parentLoader)
		defineClass(classLoader, command.root)
		command.supports.collect { defineClass(classLoader, it) }
		def input = new ByteArrayInputStream(command.instance)
		def ois = new ClassLoaderConfigurableObjectInputStream(classLoader, input)
		ois.readObject()
	}

	protected invoke(instance, arg = null) {
		use(GaelykCategory) {
			if (instance.maximumNumberOfParameters < 1) {
				instance()
			} else {
				instance(arg)
			}
		}
	}

	protected Class defineClass(ClassLoader classLoader, byte[] bytes) {
		classLoader.defineClass(null, bytes, 0, bytes.length)
	}

}
