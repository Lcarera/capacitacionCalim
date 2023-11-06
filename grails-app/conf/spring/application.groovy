grails.gorm.default.mapping = {
	'user-type' (type: org.jadira.usertype.dateandtime.joda.PersistentLocalDate, class: org.joda.time.LocalDate)
	'user-type' (type: org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime, class: org.joda.time.LocalDateTime)
}

jodatime.format.html5 = true
jodatime.format.org.joda.time.DateTime='dd/MM/yyyy HH:mm:ss z ZZ'
jodatime.format.org.joda.time.LocalDate='dd/MM/yyyy'
jodatime.format.org.joda.time.LocalDateTime='dd/MM/yyyy HH:mm:ss'