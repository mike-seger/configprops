import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jackson.JsonComponent
import kotlin.reflect.full.declaredMemberProperties

@JsonComponent
class ExcludeFieldsFilterSerializer : JsonSerializer<Any>() {
    private val excludedFields = setOf("\$\$beanFactory", "\$\$EnhancerBySpringCGLIB")

    override fun serialize(value: Any?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value != null && gen != null) {
            gen.writeStartObject()
            value::class.declaredMemberProperties.forEach { prop ->
                val propName = prop.name
                println(propName)
                if (!propName.contains("\$") || propName.contains("attr")) {
                    val propValue = prop.getter.call(value)
                    gen.writeObjectField(propName, propValue)
                }
            }
            gen.writeEndObject()
        }
    }
}
