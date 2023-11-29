package spring

import capacitacioncalim.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    localeResolver(org.springframework.web.servlet.i18n.SessionLocaleResolver){
		defaultLocale= new java.util.Locale('es');
	}
}
