package zzleep.communicator.databaseService;

import models.Command;
import models.CurrentData;
import zzleep.communicator.repository.DatabaseContext;
import zzleep.communicator.repository.DatabaseContextImpl;

import java.util.ArrayList;

public class DatabaseServiceImpl implements DatabaseService{

    private DatabaseContext dbContext;

    public DatabaseServiceImpl()
    {
        this.dbContext = new DatabaseContextImpl();
    }

    @Override
    public void putDataInDatabase(CurrentData data) {

        dbContext.insert(""+data.getCo2Data()+","+data.getTemperatureData()+","+data.getHumidityData()+","+data.getSoundData()+","+data.getSource()+""+data.getTimeStamp() );
    }

    @Override
    public ArrayList<Command> getUpdates() {
        ArrayList<Command> commands = new ArrayList<>();

        ArrayList<String> startDevices = dbContext.getActiveSleeps();
        for (String source : startDevices) {
            Command command = new Command(source, 'D', 1);
            commands.add(command);
        }

        ArrayList<String> stopDevices = dbContext.getStoppedSleeps();
        for (String source : stopDevices) {
            Command command = new Command(source, 'D', 0);
            commands.add(command);
        }

        ArrayList<String> startVentilation = dbContext.getStartVentilation();
        for (String source : startVentilation) {
            Command command = new Command(source, 'V', 1);
            commands.add(command);
        }

        ArrayList<String> stopVentilation = dbContext.getStopVentilation();
        for (String source : stopVentilation) {
            Command command = new Command(source, 'V', 0);
            commands.add(command);
        }

        return commands;
    }
}
