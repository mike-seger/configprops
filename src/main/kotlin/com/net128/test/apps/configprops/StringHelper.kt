package com.net128.test.apps.configprops

object StringHelper {
    fun isSpringBean(bean: Any): Boolean {
        return bean.javaClass.name.matches((".*[\$][\$].*$springProxySuffix.*").toRegex())
    }

    fun springBeanClass(bean: Any) : Class<*> {
        return Class.forName(bean.javaClass.name.replace(("[\$][\$]$springProxySuffix.*").toRegex(), ""))
    }

    private const val springProxySuffix = "SpringCGLIB"

    fun isSpringBeanProperty(name: String) : Boolean {
        return name.startsWith("CGLIB$") || name == "\$\$beanFactory"
    }
}