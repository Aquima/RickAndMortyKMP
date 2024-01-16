//
//  CharacterItemView.swift
//  iosApp
//
//  Created by Raul Quispe on 16/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import CachedAsyncImage


struct CharacterItemView: View {

    @Binding var character: CharacterItem

    @State var animated: Bool = false
    var body: some View {
        HStack {
            imageCharacterLoader
            VStack(alignment: .leading) {
                Text(character.name)
                    .id("CharacterName")
                Text(character.status.name)
                    .id("CharacterStatus")
            }
        }
        .opacity(animated ? 1.0 : 0.2)
        .offset(CGSize(width: 0, height: animated ? -10 : 0))
        .onAppear {
            withAnimation(.easeInOut(duration: 1).delay(0.1)) {
                animated.toggle()
             }
        }.onDisappear {
            animated.toggle()
        }
    }

    var imageCharacterLoader: some View {
        let boxSize = 150.00
        return CachedAsyncImage(url: .init(string: character.image)) { image in
            image.resizable()
                .frame(width: boxSize, height: boxSize)
                .border(Color.black.opacity(0.2), width: 2)
                .id("CharacterImage")
        } placeholder: {
            ProgressView()
                .frame(width: boxSize, height: boxSize,
                       alignment: .center)
                .border(Color.black.opacity(0.2), width: 2)
        }
    }
}

#Preview {
    let rm = CharacterItem(id: 1, name: "Rick", status: .alive, species: .alien, image: "https://rickandmortyapi.com/api/character/avatar/1.jpeg")
    return CharacterItemView(character: .constant(rm))
}

