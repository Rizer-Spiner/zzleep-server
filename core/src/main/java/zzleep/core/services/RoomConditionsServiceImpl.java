package zzleep.core.services;

import org.springframework.stereotype.Component;
import zzleep.core.models.RoomCondition;
import zzleep.core.repositories.AuthorizationService;
import zzleep.core.repositories.RoomConditionsRepository;


@Component
public class RoomConditionsServiceImpl extends ServiceBase implements RoomConditionsService {


    private final AuthorizationService authorizationService;
    private final RoomConditionsRepository repository;

    public RoomConditionsServiceImpl(AuthorizationService authorizationService, RoomConditionsRepository repository) {
        this.authorizationService = authorizationService;
        this.repository = repository;
    }

    @Override
    public Response<RoomCondition> getCurrent(Authorized<String> request) {
        if(!authorizationService.userHasDevice(request.getUserId(), request.getModel())) return unauthorized();

        try {
            return success(repository.getCurrent(request.getModel()));
        } catch (RoomConditionsRepository.SleepNotFoundException e) {
            return notFound();
        } catch (RoomConditionsRepository.NoDataException e) {
            return noContent();
        }
    }

    @Override
    public Response<RoomCondition> getLatest(Authorized<String> request) {
        if (!authorizationService.userHasDevice(request.getUserId(), request.getModel()))
            return unauthorized();
        try {
            return success(
                repository.getLatest(request.getModel())
            );
        } catch (RoomConditionsRepository.SleepNotFoundException e) {
            return notFound();
        } catch (RoomConditionsRepository.NoDataException e) {
            return noContent();
        }
    }

}
