package com.example.mapkittest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.geometry.LinearRing;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polygon;
import com.yandex.mapkit.geometry.Polyline;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CircleMapObject;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.PolygonMapObject;
import com.yandex.mapkit.map.PolylineMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.AnimatedImageProvider;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.ui_view.ViewProvider;

import java.util.ArrayList;
import java.util.Random;

/**
 * This example shows how to add simple objects such as polygons, circles and polylines to the map.
 * It also shows how to display images instead.
 */
public class MainActivity extends Activity {
    /**
     * Replace "your_api_key" with a valid developer key.
     * You can get it at the https://developer.tech.yandex.ru/ website.
     */
    private final String MAPKIT_API_KEY = "56536952-89db-4660-8a11-275b4a18135b";
    private final Point CAMERA_TARGET = new Point(55.554047, 37.421903);
    private final Point ANIMATED_RECTANGLE_CENTER = new Point(55.552831, 37.418256);
    private final Point POLYLINE_CENTER = CAMERA_TARGET;
    private final Point DRAGGABLE_PLACEMARK_CENTER = new Point(55.556063, 37.417625);
    private final double OBJECT_SIZE = 0.0005;

    private MapView mapView;
    private MapObjectCollection mapObjects;
    private Handler animationHandler;

    private final static Point[] circles = {
            new Point(55.553296, 37.417797),
            new Point(55.551642, 37.422131),

            new Point(55.553868, 37.421423),
            new Point(55.553186, 37.425972),

            new Point(55.553186, 37.425972),
            new Point(55.555631, 37.418419),
            new Point(55.552648, 37.424371),

            new Point(55.553917, 37.420618),
            new Point(55.551073, 37.418411),

            new Point(55.554815, 37.419212),
            new Point(55.554785, 37.421953),
            new Point(55.554230, 37.419960),

            new Point(55.555703, 37.419728),
            new Point(55.555874, 37.421917),
            new Point(55.555671, 37.425466),

            new Point(55.555990, 37.417504),
            new Point(55.555664, 37.421839),
            new Point(55.555697, 37.426181)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        mapView = (MapView)findViewById(R.id.mapview);
        mapView.getMap().move(
                new CameraPosition(CAMERA_TARGET, 15.0f, 0.0f, 0.0f));
        mapObjects = mapView.getMap().getMapObjects().addCollection();
        animationHandler = new Handler();
        createMapObjects();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    private void createMapObjects() {
        AnimatedImageProvider animatedImage = AnimatedImageProvider.fromAsset(this, "animation.png");
        ArrayList<Point> rectPoints = new ArrayList<>();
        rectPoints.add(new Point(
                ANIMATED_RECTANGLE_CENTER.getLatitude() - OBJECT_SIZE,
                ANIMATED_RECTANGLE_CENTER.getLongitude() - OBJECT_SIZE));
        rectPoints.add(new Point(
                ANIMATED_RECTANGLE_CENTER.getLatitude() - OBJECT_SIZE,
                ANIMATED_RECTANGLE_CENTER.getLongitude() + OBJECT_SIZE));
        rectPoints.add(new Point(
                ANIMATED_RECTANGLE_CENTER.getLatitude() + OBJECT_SIZE,
                ANIMATED_RECTANGLE_CENTER.getLongitude() + OBJECT_SIZE));
        rectPoints.add(new Point(
                ANIMATED_RECTANGLE_CENTER.getLatitude() + OBJECT_SIZE,
                ANIMATED_RECTANGLE_CENTER.getLongitude() - OBJECT_SIZE));
        PolygonMapObject rect = mapObjects.addPolygon(
                new Polygon(new LinearRing(rectPoints), new ArrayList<LinearRing>()));
        rect.setStrokeColor(Color.TRANSPARENT);
        rect.setFillColor(Color.TRANSPARENT);
        rect.setAnimatedImage(animatedImage, 32.0f);

        for (int i = 0; i < circles.length; i++) {
            CircleMapObject circle = mapObjects.addCircle(
                    new Circle(circles[i], 25), Color.BLACK, 2, Color.RED);
            circle.setZIndex(100.0f);
            circle.setUserData(new CircleMapObjectUserData(i, "Tappable circle"));
            if (i<2) {
                circle.addTapListener(new MapObjectTapListener() {
                    @Override
                    public boolean onMapObjectTap(MapObject mapObject, Point point) {
                        if (mapObject instanceof CircleMapObject) {
                            CircleMapObject circle = (CircleMapObject)mapObject;

                            Object userData = circle.getUserData();
                            if (userData instanceof CircleMapObjectUserData) {
                                CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                                Intent intent = new Intent(getApplicationContext(), Ber.class);
                                startActivity(intent);
                            }
                        }
                        return true;
                    }
                });
            } else if (i < 4) {
                circle.addTapListener(new MapObjectTapListener() {
                    @Override
                    public boolean onMapObjectTap(MapObject mapObject, Point point) {
                        if (mapObject instanceof CircleMapObject) {
                            CircleMapObject circle = (CircleMapObject)mapObject;

                            Object userData = circle.getUserData();
                            if (userData instanceof CircleMapObjectUserData) {
                                CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                                Intent intent = new Intent(getApplicationContext(), Dub.class);
                                startActivity(intent);
                            }
                        }
                        return true;
                    }
                });
            } else if (i < 7) {
                circle.addTapListener(new MapObjectTapListener() {
                    @Override
                    public boolean onMapObjectTap(MapObject mapObject, Point point) {
                        if (mapObject instanceof CircleMapObject) {
                            CircleMapObject circle = (CircleMapObject)mapObject;

                            Object userData = circle.getUserData();
                            if (userData instanceof CircleMapObjectUserData) {
                                CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                                Intent intent = new Intent(getApplicationContext(), Klen.class);
                                startActivity(intent);
                            }
                        }
                        return true;
                    }
                });
            } else if (i < 9) {
                circle.addTapListener(new MapObjectTapListener() {
                    @Override
                    public boolean onMapObjectTap(MapObject mapObject, Point point) {
                        if (mapObject instanceof CircleMapObject) {
                            CircleMapObject circle = (CircleMapObject)mapObject;

                            Object userData = circle.getUserData();
                            if (userData instanceof CircleMapObjectUserData) {
                                CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                                Intent intent = new Intent(getApplicationContext(), Lipa.class);
                                startActivity(intent);
                            }
                        }
                        return true;
                    }
                });
            } else if (i < 12) {
                circle.addTapListener(new MapObjectTapListener() {
                    @Override
                    public boolean onMapObjectTap(MapObject mapObject, Point point) {
                        if (mapObject instanceof CircleMapObject) {
                            CircleMapObject circle = (CircleMapObject)mapObject;

                            Object userData = circle.getUserData();
                            if (userData instanceof CircleMapObjectUserData) {
                                CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                                Intent intent = new Intent(getApplicationContext(), Sosna.class);
                                startActivity(intent);
                            }
                        }
                        return true;
                    }
                });
            } else if (i < 15) {
                circle.addTapListener(new MapObjectTapListener() {
                    @Override
                    public boolean onMapObjectTap(MapObject mapObject, Point point) {
                        if (mapObject instanceof CircleMapObject) {
                            CircleMapObject circle = (CircleMapObject)mapObject;

                            Object userData = circle.getUserData();
                            if (userData instanceof CircleMapObjectUserData) {
                                CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                                Intent intent = new Intent(getApplicationContext(), Iva.class);
                                startActivity(intent);
                            }
                        }
                        return true;
                    }
                });
            } else if (i < 18) {
                circle.addTapListener(new MapObjectTapListener() {
                    @Override
                    public boolean onMapObjectTap(MapObject mapObject, Point point) {
                        if (mapObject instanceof CircleMapObject) {
                            CircleMapObject circle = (CircleMapObject)mapObject;

                            Object userData = circle.getUserData();
                            if (userData instanceof CircleMapObjectUserData) {
                                CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                                Intent intent = new Intent(getApplicationContext(), Olha.class);
                                startActivity(intent);
                            }
                        }
                        return true;
                    }
                });
            }
        }

        ArrayList<Point> polylinePoints = new ArrayList<>();
        polylinePoints.add(new Point(
                POLYLINE_CENTER.getLatitude() + OBJECT_SIZE,
                POLYLINE_CENTER.getLongitude()- OBJECT_SIZE));
        polylinePoints.add(new Point(
                POLYLINE_CENTER.getLatitude() - OBJECT_SIZE,
                POLYLINE_CENTER.getLongitude()- OBJECT_SIZE));
        polylinePoints.add(new Point(
                POLYLINE_CENTER.getLatitude(),
                POLYLINE_CENTER.getLongitude() + OBJECT_SIZE));

        PolylineMapObject polyline = mapObjects.addPolyline(new Polyline(polylinePoints));
        polyline.setStrokeColor(Color.BLACK);
        polyline.setZIndex(100.0f);

        PlacemarkMapObject mark = mapObjects.addPlacemark(DRAGGABLE_PLACEMARK_CENTER);
        mark.setOpacity(1f);
        mark.setIcon(ImageProvider.fromResource(this, R.drawable.pointer));
        mark.addTapListener(new MapObjectTapListener() {
            @Override
            public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
                Intent intent = new Intent(getApplicationContext(), Ber.class);
                startActivity(intent);
                return true;
            }
        });

        createPlacemarkMapObjectWithViewProvider();
    }

    // Strong reference to the listener.
    private MapObjectTapListener circleMapObjectTapListener = new MapObjectTapListener() {
        @Override
        public boolean onMapObjectTap(MapObject mapObject, Point point) {
            if (mapObject instanceof CircleMapObject) {
                CircleMapObject circle = (CircleMapObject)mapObject;

                Object userData = circle.getUserData();
                if (userData instanceof CircleMapObjectUserData) {
                    CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                    Intent intent = new Intent(getApplicationContext(), Ber.class);
                    startActivity(intent);
                }
            }
            return true;
        }
    };

    private class CircleMapObjectUserData {
        final int id;
        final String description;

        CircleMapObjectUserData(int id, String description) {
            this.id = id;
            this.description = description;
        }
    }

    private void createPlacemarkMapObjectWithViewProvider() {
        final TextView textView = new TextView(this);
        final int[] colors = new int[] { Color.RED, Color.GREEN, Color.BLACK };
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);

        textView.setTextColor(Color.RED);
        textView.setText("Hello, World!");

        final ViewProvider viewProvider = new ViewProvider(textView);
        final PlacemarkMapObject viewPlacemark =
                mapObjects.addPlacemark(new Point(55.554522, 37.418620), viewProvider);

        final Random random = new Random();
        final int delayToShowInitialText = 5000;  // milliseconds
        final int delayToShowRandomText = 500; // milliseconds;

        // Show initial text `delayToShowInitialText` milliseconds and then
        // randomly change text in textView every `delayToShowRandomText` milliseconds
        animationHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final int randomInt = random.nextInt(1000);
                textView.setText("Some text version " + randomInt);
                textView.setTextColor(colors[randomInt % colors.length]);
                viewProvider.snapshot();
                viewPlacemark.setView(viewProvider);
                animationHandler.postDelayed(this, delayToShowRandomText);
            }
        }, delayToShowInitialText);
    }
}