package com.kaushikam.strategypattern.shared

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class SpringApplicationContext: ApplicationContextAware {

	override fun setApplicationContext(applicationContext: ApplicationContext) {
		CONTEXT = applicationContext
	}

	companion object {
		@JvmStatic
		private var CONTEXT: ApplicationContext? = null

		@JvmStatic
		fun <T> getObjectOfClass(clazz: Class<T>): T {
			return CONTEXT!!.getBean(clazz)
		}
	}
}