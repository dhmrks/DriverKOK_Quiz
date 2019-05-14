/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

	<script> 
                        function startTimer(duration, display) {
                              var timer = duration, minutes, seconds;
                              setInterval(function () {
                                  minutes = parseInt(timer / 60, 10);
                                  seconds = parseInt(timer % 60, 10);

                                  minutes = minutes < 10 ? "0" + minutes : minutes;
                                  seconds = seconds < 10 ? "0" + seconds : seconds;

                                  display.textContent = minutes + ":" + seconds;
                                    
                                  if (--timer < 0) {
                                      alert("Time is up!");
                                  }
                              }, 1000);
                          }
                           var time;
                          window.onload = function () {
                               time=new Date();
                              var fiveMinutes = 60 * 5,
                                  display = document.querySelector('#time');
                              startTimer(fiveMinutes, display);
                          };
        </script><div id="time" class="time_box">05:00</div>

