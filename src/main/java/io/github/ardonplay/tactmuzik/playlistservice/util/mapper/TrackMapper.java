package io.github.ardonplay.tactmuzik.playlistservice.util.mapper;

import io.github.ardonplay.tactmuzik.playlistservice.data.NewTrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.inner.TrackDto;
import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistTrackEntity;
import io.github.ardonplay.tactmuzik.playlistservice.entity.TrackEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TrackMapper {

  TrackDto mapTrackEntityToTrackDto(PlaylistTrackEntity source);

  TrackEntity mapNewTrackDtoToTrackEntity(NewTrackDto source);

}
