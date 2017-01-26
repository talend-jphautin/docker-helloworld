$(document).ready(function() {
    var greetingsTextTemplate   = $("#greetings-template").html();
    var greetingsTemplate = Handlebars.compile(greetingsTextTemplate);

    Handlebars.registerHelper("formatDate", function(datetime, format) {
      if (moment) {
        return moment(datetime).format(format);
      }
      else {
        return datetime;
      }
    });

    $('#send').on('click', function(e) {
        // Je récupère les valeurs
        var name = $('#name').val();
        if (name=="") {
            name = 'World';
        }
        // Envoi de la requête HTTP en mode asynchrone
        $.ajax({
            url: '/api/greeting/'+name,
            type: 'post', // La méthode indiquée dans le formulaire (get ou post)
            dataType: 'json',
            statusCode: {
               404: function() {
                  alert( "service is not available." );
               },
               200: function(message) {
                  $("p.result").html("<div>"+message.content+"</div>");
               }
            }
        });
        return false;
    });

    $('#check').on('click', function(e) {
        // Je récupère les valeurs
        var name = $('#name').val();
        if (name=="") {
            name = 'World';
        }
        // Envoi de la requête HTTP en mode asynchrone
        $.ajax({
            url: '/api/greeting/'+name,
            type: 'get', // La méthode indiquée dans le formulaire (get ou post)
            dataType: 'json',
            statusCode: {
               404: function() {
                  alert( "service is not available." );
               },
               200: function(greetings) {
                  $("p.result").html(greetingsTemplate(greetings));
               }
            }
        });
        return false;
    });
});