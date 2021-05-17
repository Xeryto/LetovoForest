package com.example.mapkittest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

public class MainActivity extends Activity {
    private final String MAPKIT_API_KEY = "56536952-89db-4660-8a11-275b4a18135b";
    private final Point CAMERA_TARGET = new Point(55.554047, 37.421903);

    private MapView mapView;
    private MapObjectCollection mapObjects;

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
        createMapObjects();
        Button contacts;
        contacts = findViewById(R.id.contacts);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Contacts.class);
                startActivity(intent);
            }
        });
        contacts.setBackgroundColor(Color.GREEN);
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
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(55.553296, 37.417797));
        points.add(new Point(55.551642, 37.422131));

        points.add(new Point(55.553868, 37.421423));
        points.add(new Point(55.551254, 37.419105));

        points.add(new Point(55.553186, 37.425972));
        points.add(new Point(55.555631, 37.418419));
        points.add(new Point(55.551940, 37.423768));

        points.add(new Point(55.553746, 37.420618));
        points.add(new Point(55.551073, 37.418411));

        points.add(new Point(55.555249, 37.418376));
        points.add(new Point(55.554795, 37.421526));
        points.add(new Point(55.554230, 37.419960));

        points.add(new Point(55.555703, 37.419728));
        points.add(new Point(55.555874, 37.421917));
        points.add(new Point(55.555671, 37.425466));

        points.add(new Point(55.555990, 37.417504));
        points.add(new Point(55.555592, 37.422362));
        points.add(new Point(55.555697, 37.426181));

        for (int i = 0; i < points.size(); i++) {
            CircleMapObject circle = mapObjects.addCircle(
                    new Circle(points.get(i), 25), Color.BLACK, 2, Color.RED);
            circle.setZIndex(100.0f);
            circle.setUserData(new CircleMapObjectUserData(i));
            circle.addTapListener(circleMapObjectTapListener);
        }
    }

    private MapObjectTapListener circleMapObjectTapListener = new MapObjectTapListener() {
        @Override
        public boolean onMapObjectTap(MapObject mapObject, Point point) {
            if (mapObject instanceof CircleMapObject) {
                CircleMapObject circle = (CircleMapObject)mapObject;

                Object userData = circle.getUserData();
                if (userData instanceof CircleMapObjectUserData) {
                    CircleMapObjectUserData circleUserData = (CircleMapObjectUserData)userData;

                    Intent intent = null;

                    if (circleUserData.id < 2) {
                        intent = new Intent(getApplicationContext(), Ber.class);
                    } else if (circleUserData.id < 4) {
                        intent = new Intent(getApplicationContext(), Dub.class);
                    } else if (circleUserData.id < 7) {
                        intent = new Intent(getApplicationContext(), Klen.class);
                    } else if (circleUserData.id < 9) {
                        intent = new Intent(getApplicationContext(), Lipa.class);
                    } else if (circleUserData.id < 12) {
                        intent = new Intent(getApplicationContext(), Sosna.class);
                    } else if (circleUserData.id < 15) {
                        intent = new Intent(getApplicationContext(), Iva.class);
                    } else if (circleUserData.id < 18) {
                        intent = new Intent(getApplicationContext(), Olha.class);
                    }
                    startActivity(intent);
                }
            }
            return true;
        }
    };

    private class CircleMapObjectUserData {
        final int id;

        CircleMapObjectUserData(int id) {
            this.id = id;
        }
    }
}