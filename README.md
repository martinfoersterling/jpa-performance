# jpa-performance
Ressourcen zu einem Lightning Talk über typische Fehler beim Umgang mit Data Access über JPA

Die Unit-Tests unter mafoe.jpaperformance.test.usecase1 und mafoe.jpaperformance.test.usecase2 stellen die eigentliche
Vorführbasis dar. Dort wird anhand zweier use cases eine fachliche Anforderung und die jeweilige Umsetzung illustriert.
Die Tests sind durchnummeriert und zeigen kontinuierliche Verbesserung beim data access auf.

Einstellmöglichkeiten:
 - in den hibernate.properties kann man mit dem Property hibernate.show_sql=[true|false] einstellen, ob das ausgeführte
 SQL geloggt werden soll (ohne Parameter)

Talking points stehen hier anstatt direkt in den Tests, weil die Tests dem Publikum gezeigt werden und dort nicht gleich
die Lösung sichtbar sein soll.

Talking points, Use Case 1:

1. Test: Schlechtmöglichste Implementierung. Lädt alle Daten von der DB, obwohl wir nicht alle brauchen. Es werden
katastrophale 4 selects abgesetzt, weil das findAll() alle Modellgruppen lädt, aber wir für das Filtern auch noch die
Marke brauchen, die dann von Hibernate automatisch nachgeladen werden müssen.
2. Test: Erst Marke laden, dann alle daran hängenden Modellgruppen. Deutlich besser, benötigt aber immer noch 2 selects.
Idealerweise brauchen wir nur 1 select.
3. Test: Daten werden in 1 select geladen, aber für das DTO brauchen wir noch die Marke, die wieder von Hibernate
automatisch nachgeladen werden muss.
4. Test: Mithilfe eines LEFT JOIN FETCH werden alle Daten mit 1 select geladen. Wegen des FETCHs wird allerdings die
übertragende Datenmenge etwas größer, denn die Modellgruppen werden mit ihrer Marke im ResultSet ausmultipliziert. Ist
aber vernachlässigbar gegenüber dem Overhead eines zweiten selects.

Talking points, Use Case 2:

1. Test: Daumenregel: Nie data access in einer Schleife durchführen, wenn vermeidbar. Hier sieht man viele unnötige selects.
2. Test: Mit dem bekannten LEFT JOIN FETCH aus Use Case 1 und dem Laden der Daten ohne Schleife reduziert man den data
access wieder auf 1 select.
3. Test: Setzen einer hohen fetch size beschleunigt das Laden der Daten übers Netzwerk.
4. Test: Um zu vermeiden, dass wir die Familie-Entität mit dem sehr großen, aber gar nicht benötigten Attribut "beschreibung"
laden, wird hier eine jpa constructor expression eingesetzt.
