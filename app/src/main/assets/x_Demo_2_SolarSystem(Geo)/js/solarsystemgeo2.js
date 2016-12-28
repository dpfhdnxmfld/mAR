var Geo = {


}

var Solar = {
    planetsInfo: null,

    init: function() {
        var distanceFactor = 580.2;

        /* null means: use relative to user, sun is NORTH to the user */
       var locationReal = new AR.GeoLocation(127.051039, 37.488334, 100.);
        var locationSun = new AR.RelativeLocation(null, 1000, 0, 300);

        /* sizes & distances are far away from real values! used these scalings to be able to show within user range */
        var sizeFactor = 0.5;
        var sizeEarth = 12.8 * 25;

        /* every object in space has a name, location and a circle (drawable) */
        var sunImg = new AR.ImageResource("assets/Sun-icon.png");

        var indicatorImg = new AR.ImageResource("assets/indi.png");

        var sunSize = (((109 * sizeEarth) / sizeEarth) * 0.3) * sizeFactor;


        var sun = {
            name: "Sun",
            distance: 0,
            location: locationSun,
            imgDrawable: new AR.ImageDrawable(sunImg, sunSize),
            size: sunSize,
            description: "The Sun is the star at the center of the Solar System. It is almost perfectly spherical and consists of hot plasma interwoven with magnetic fields.",
            mass: "2&nbsp;10<sup>30</sup>&nbsp;kg",
            diameter: "1,392,684&nbsp;km"
        };




        /* put sun, planets (and pluto) in an array */
        this.planetsInfo = [sun];

        /* create helper array to create goeObjects out of given information */
        var planetsGeoObjects = [];
        for (var i = 0; i < this.planetsInfo.length; i++) {

            /* show name of object below*/
            var label = new AR.Label(this.planetsInfo[i].name, 3, {
                offsetY: -this.planetsInfo[i].size / 2,
                verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP,
                opacity: 0.9,
                zOrder: 1,
                style: {
                    textColor: '#FFFFFF',
                    backgroundColor: '#00000005'
                }
            });

            /* drawable in cam of object -> image and label */
            var drawables = [];
            drawables[0] = this.planetsInfo[i].imgDrawable;
            drawables[1] = label;

            /* Create objects in AR*/
            planetsGeoObjects[i] = new AR.GeoObject(this.planetsInfo[i].location, {
                drawables: {
                    cam: drawables
                },
                onClick: this.planetClicked(this.planetsInfo[i])
            });
            if (i > 0) {
              //  this.animate(this.planetsInfo[i]);
              ;
            } else {
                var sunHackAnim = new AR.PropertyAnimation(this.planetsInfo[i].location, 'northing', 10000, 10000, 1000, {
                    type: AR.CONST.EASING_CURVE_TYPE.EASE_IN_SINE
                });
                sunHackAnim.start(-1);
            }
        }

        // Add indicator to sun
        var imageDrawable = new AR.ImageDrawable(indicatorImg, 0.1, {
            verticalAnchor: AR.CONST.VERTICAL_ANCHOR.TOP
        });
        planetsGeoObjects[0].drawables.addIndicatorDrawable(imageDrawable);
    },
/*
    animate: function(planet) {
        var relLocation = planet.location;
        var distance = planet.distance;
        var roundingTime = distance * 2 * 2;

        var northSouthAnimation1 = new AR.PropertyAnimation(relLocation, 'northing', distance * 1, distance * 0, roundingTime / 4, {
            type: AR.CONST.EASING_CURVE_TYPE.EASE_IN_SINE
        });
        var eastWestAnimation1 = new AR.PropertyAnimation(relLocation, 'easting', distance * 0, distance * 1, roundingTime / 4, {
            type: AR.CONST.EASING_CURVE_TYPE.EASE_OUT_SINE
        });

        var northSouthAnimation2 = new AR.PropertyAnimation(relLocation, 'northing', distance * 0, distance * -1, roundingTime / 4, {
            type: AR.CONST.EASING_CURVE_TYPE.EASE_OUT_SINE
        });
        var eastWestAnimation2 = new AR.PropertyAnimation(relLocation, 'easting', distance * 1, distance * 0, roundingTime / 4, {
            type: AR.CONST.EASING_CURVE_TYPE.EASE_IN_SINE
        });

        var northSouthAnimation3 = new AR.PropertyAnimation(relLocation, 'northing', distance * -1, distance * 0, roundingTime / 4, {
            type: AR.CONST.EASING_CURVE_TYPE.EASE_IN_SINE
        });
        var eastWestAnimation3 = new AR.PropertyAnimation(relLocation, 'easting', distance * 0, distance * -1, roundingTime / 4, {
            type: AR.CONST.EASING_CURVE_TYPE.EASE_OUT_SINE
        });

        var northSouthAnimation4 = new AR.PropertyAnimation(relLocation, 'northing', distance * 0, distance * 1, roundingTime / 4, {
            type: AR.CONST.EASING_CURVE_TYPE.EASE_OUT_SINE
        });
        var eastWestAnimation4 = new AR.PropertyAnimation(relLocation, 'easting', distance * -1, distance * 0, roundingTime / 4, {
            type: AR.CONST.EASING_CURVE_TYPE.EASE_IN_SINE
        });

        var q1 = new AR.AnimationGroup(AR.CONST.ANIMATION_GROUP_TYPE.PARALLEL, [northSouthAnimation1, eastWestAnimation1]);
        var q2 = new AR.AnimationGroup(AR.CONST.ANIMATION_GROUP_TYPE.PARALLEL, [northSouthAnimation2, eastWestAnimation2]);
        var q3 = new AR.AnimationGroup(AR.CONST.ANIMATION_GROUP_TYPE.PARALLEL, [northSouthAnimation3, eastWestAnimation3]);
        var q4 = new AR.AnimationGroup(AR.CONST.ANIMATION_GROUP_TYPE.PARALLEL, [northSouthAnimation4, eastWestAnimation4]);

        var cicularAnimationGroup = new AR.AnimationGroup(AR.CONST.ANIMATION_GROUP_TYPE.SEQUENTIAL, [q4, q1, q2, q3]);

        cicularAnimationGroup.start(-1);
    },
*/
    planetClicked: function(planet) {
        return function() {
            document.getElementById("info").setAttribute("class", "info");
            document.getElementById("name").innerHTML = planet.name;
            document.getElementById("mass").innerHTML = planet.mass;
            document.getElementById("diameter").innerHTML = planet.diameter;
            document.getElementById("info").setAttribute("class", "infoVisible");
        };
    }
};

Solar.init();