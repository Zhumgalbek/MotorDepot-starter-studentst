package com.company.service;


import com.company.entities.Driver;
import com.company.entities.State;
import com.company.entities.Truck;

import java.util.*;

import static com.company.Main.*;

public class ServiceImpl implements Service {
    final static Scanner sc = new Scanner(System.in);
    final static Scanner scA = new Scanner(System.in);
    List<Truck> trucks = new ArrayList<>(List.of(GSON.fromJson(readTtuck(), Truck[].class)));
    List<Driver> drivers = new ArrayList<>(List.of(GSON.fromJson(readDriver(), Driver[].class)));

    public List<Truck> getTrucks() {
        return trucks;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public void changeDriver() {
        try {
            System.out.print("\nid truck : ");
            int truckId = sc.nextInt();
            for (Truck truck : trucks) {
                if (truck.getId() == truckId && truck.getState() != State.ROUTE) {
                    int counter = 0;
                    for (Driver d : drivers) {
                        if (d.getTruckName().equals(truck.getTruckName())){
                            d.setTruckName(" ");
                        }

                        if (d.getTruckName().equals("") && counter == 0) {
                            counter++;
                            truck.setDriver(d.getName());
                            d.setTruckName(truck.getTruckName());
                        }

                        if (d.getTruckName().equals(" ")){
                            d.setTruckName("");
                        }
                        else if (truckId != truck.getId()) {
                            System.out.println("нету такой цифры !! ");
                        }
                    }
                } else if (truck.getId() == truckId && truck.getState() == State.ROUTE) {
                    throw new Exception("Грузовик уже в пути не можем поменять водитель!");
                }
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            sc.nextLine();
        }

    }



    @Override
    public void startDriving() {
        System.out.print("\nid truckid : ");
        int truckId = scA.nextInt();
        try {
            for (Truck truck : trucks) {
                if (truck.getState() != State.ROUTE && truck.getId() == truckId && !truck.getDriver().equals(" ")) {
                    truck.setState(State.ROUTE);
                } else if (truck.getId() == truckId && truck.getState() == State.ROUTE) {
                    throw new Exception("Грузовик уже в пути !");
                }
                else if (!truck.getState().equals(" ") && truckId == truck.getId()){
                    throw new Exception("нету водителя!!!");
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public void startRepair() {
        try {
            System.out.print("\nid truckid : ");
            int truckId = scA.nextInt();
            for (Truck truck : trucks) {
                if (truck.getId() == truckId && truck.getState() != State.REPAIR) {
                    truck.setState(State.REPAIR);
                    truck.setDriver(" ");
                } else if (truck.getId() == truckId && truck.getState() == State.REPAIR) {
                    throw new NullPointerException("truck уже ремонтто !!");
                }
            }
        } catch (NullPointerException nullPointerException) {
            System.out.println(nullPointerException.getMessage());
        } catch (Exception e) {
            System.out.println("Введите id truck !!!!!!");
        }

    }

    @Override
    public void changeTruckState() {
        try {
            System.out.print("\nid truckid : ");
            int truckId = scA.nextInt();
            for (Truck truck : trucks) {
                if (truck.getId() == truckId && truck.getState() != State.BASE) {
                    truck.setState(State.BASE);
                } else if (truck.getId() == truckId && truck.getState() == State.BASE) {
                    throw new NullPointerException("truck уже базада !!");
                }
            }
        } catch (NullPointerException nullPointerException) {
            System.out.println(nullPointerException.getMessage());
        } catch (Exception exception) {
            System.out.println("Введите id truck !!!!!!");
        }

    }


    public void truk() {
        System.out.println( );
        trucks.forEach(System.out::println);
    }
}













