package io.github.ardonplay.tactmuzik.playlistservice.util.mapper;

import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistBaseInfoDto;
import io.github.ardonplay.tactmuzik.playlistservice.data.PlaylistByIdDto;
import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistByuserIdEntity;
import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistEntity;
import io.github.ardonplay.tactmuzik.playlistservice.entity.PlaylistTrackEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PlaylistMapper {

    PlaylistByIdDto mapPlaylistEntityToPlaylistByIdDto(PlaylistEntity source);

    PlaylistBaseInfoDto mapPlaylistByUserIdEntityToPlaylistByIdDto(PlaylistByuserIdEntity source);

    PlaylistBaseInfoDto mapPlaylistEntityToPlaylistBaseInfoDto(PlaylistEntity source);
}
