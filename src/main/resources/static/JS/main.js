$(document).ready(function () {
  /*------------------Navbar Shrink---------------------- */
  $(window).on("scroll", function () {
    if ($(this).scrollTop() > 90) {
      $(".navbar").addClass("navbar-shrink");
    } else {
      $(".navbar").removeClass("navbar-shrink");
    }
  });

  $(document).ready(function () {
    /*------------------ Video Popup ---------------------- */
    $(document).ready(function () {
      let videoSrc = "/Video/rr.mp4"; // The path to your video file
      $(".video-play-btn, .video-popup").on("click", function () {
        if ($(".video-popup").hasClass("open")) {
          $(".video-popup").removeClass("open");
          $("#player-1").attr("src", ""); // Remove src to stop video
        } else {
          $(".video-popup").addClass("open");
          if ($("#player-1").attr("src") === "") {
            $("#player-1").attr("src", videoSrc); // Set video src when the popup opens
            $("#player-1").get(0).play(); // Play the video when the popup opens
          }
        }
      });

      // Pause and close video popup
      $(".video-popup-close").on("click", function () {
        $("#player-1").get(0).pause(); // Pause the video
        $("#player-1").attr("src", ""); // Remove the src to stop the video
        $(".video-popup").removeClass("open");
      });
    });
  });


  /* ------------------ Features Section ---------------------- */
  $(".features-carousel").owlCarousel({
    loop: true,
    margin: 0,
    autoplay: true,
    responsiveClass: true,
    responsive: {
      0: {
        items: 1,
      },
      600: {
        items: 2,
      },
      1000: {
        items: 3,
      },
    },
  });

  /* Scroll-to-top button logic */
  const scrollBtn = document.querySelector(".scroll-to-top");

  const refreshButtonVisibility = () => {
    if (document.documentElement.scrollTop <= 150) {
      scrollBtn.style.display = "none";
    } else {
      scrollBtn.style.display = "block";
    }
  };
  refreshButtonVisibility();

  scrollBtn.addEventListener("click", () => {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
  });

  document.addEventListener("scroll", (e) => {
    refreshButtonVisibility();
  });

  /* ------------------ Testimonials Section ---------------------- */
  $(".testimonials-carousel").owlCarousel({
    loop: true,
    margin: 0,
    autoplay: true,
    responsiveClass: true,
    responsive: {
      0: {
        items: 1,
      },
      600: {
        items: 2,
      },
      1000: {
        items: 3,
      },
    },
  });

  /*--------------------- ScrollIt Section ---------------------*/
  if ($.fn.scrollIt) {
    $.scrollIt({
      topOffset: -50, // Adjust offset to account for the navbar height
      scrollTime: 600, // Animation duration in milliseconds
      easing: "easeInOutExpo", // Easing pattern for smooth scrolling
    });
  }


  /*--------------------- Navbar Collapse Section ---------------------*/
  $(".nav-link").on("click", function () {
    $(".navbar-collapse").collapse("hide");
  });

  /*----------------- Toggle Theme - Light & Dark Mode -------------------*/
  function toggleTheme() {
    if (localStorage.getItem("shala-theme") !== null) {
      if (localStorage.getItem("shala-theme") === "dark") {
        $("body").addClass("dark");
      } else {
        $("body").removeClass("dark");
      }
    }
    updateIcon();
  }
  toggleTheme();
  $(".toggle-theme").on("click", function () {
    $("body").toggleClass("dark");
    if ($("body").hasClass("dark")) {
      localStorage.setItem("shala-theme", "dark");
    } else {
      localStorage.setItem("shala-theme", "light");
    }
    updateIcon();
  });

  function updateIcon() {
    if ($("body").hasClass("dark")) {
      $(".toggle-theme i").removeClass("fa-moon");
      $(".toggle-theme i").addClass("fa-sun");
    } else {
      $(".toggle-theme i").addClass("fa-moon");
      $(".toggle-theme i").removeClass("fa-sun");
    }
  }
});
