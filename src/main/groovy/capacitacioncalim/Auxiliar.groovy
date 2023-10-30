package capacitacioncalim

import java.lang.AssertionError
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import org.joda.time.Duration
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import grails.core.DefaultGrailsApplication
class Auxiliar {
	public static String printearError(e){ printearError(e, null) }
	public static String printearError(e, String mensaje){
		if (!mensaje)
			mensaje = "Error"
		String printeo = "\n\n{" + new LocalDateTime().toString("dd/MM/yyyy HH:mm:ss") + "}\t$mensaje"
		mensaje = e?.message
		if (e instanceof AssertionError){
			if (mensaje?.contains("finerror"))
				printeo += "\nMensaje: " + mensaje?.split("finerror")[0]
			else
				printeo += "\nMensaje: " + mensaje?.split("Expression:")[0]
		}
		else{
			printeo += "\nMensaje: " + mensaje
		}
		printeo += "\nStackTrace:"
		printeo += "\n" + e?.stackTrace?.findAll {
			it.toString()?.with {
				contains(".groovy:") && ! toLowerCase().with {
					contains("transaction") || contains("springsecurity") || contains(".grails.") || contains("grails.gorm")
				}
			}
		}?.collect {
			"	-$it"
		}?.join("\n")
		println printeo
		return printeo
	}
}