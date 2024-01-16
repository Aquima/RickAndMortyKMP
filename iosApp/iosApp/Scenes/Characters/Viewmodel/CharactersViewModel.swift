//
//  CharactersViewModel.swift
//  iosApp
//
//  Created by Raul Quispe on 15/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Combine
import shared
typealias CharacterItem = RMCharacter
typealias CharactersRepository = CharacterRepository

class CharactersViewModel: ObservableObject {
    
    @Published var charactersRequestState: CharactersRequestState?
    var repository: CharactersRepository
    
    init(repository: CharactersRepository) {
        self.repository = repository
    }
    
    @MainActor
    func fetchData() async {

        for await rs in  repository.getCharacter(page: 1, name: "rick") {
         charactersRequestState = rs
        }

    }
}
