package spring

// Place your Spring DSL code here
beans = {
    localeResolver(org.springframework.web.servlet.i18n.SessionLocaleResolver){
		defaultLocale= new java.util.Locale('es');
	}
}
