package com.idle.game.server.service;

import com.idle.game.core.battle.Battle;
import com.idle.game.core.formation.type.FormationAllocation;
import com.idle.game.helper.client.battle.BattleClient;
import com.idle.game.helper.client.battle.BattleFieldClient;
import com.idle.game.helper.client.battle.FormationClient;
import com.idle.game.helper.client.campaign.CampaignPathClient;
import com.idle.game.helper.client.mail.MailClient;
import com.idle.game.model.Formation;
import com.idle.game.model.Mail;
import com.idle.game.model.battle.BattleField;
import com.idle.game.model.battle.BattleSite;
import com.idle.game.model.campaign.CampaignPath;
import com.idle.game.model.campaign.UserCampaignPath;
import com.idle.game.server.repository.UserCampaignPathRepository;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael
 */
@Service
public class UserCampaignPathService {
    
    @Autowired
    private BattleClient battleClient;
    
    @Autowired
    private CampaignPathClient campaignPathClient;
    
    @Autowired
    private BattleFieldClient battleFieldClient;
    
    @Autowired
    private FormationClient formationClient;
    
    @Autowired
    private MailClient mailClient;
    
    @Autowired
    private UserCampaignPathRepository userCampaignPathRepository;
    
    public UserCampaignPath findByUserId(String userId) {
        
        UserCampaignPath ret = userCampaignPathRepository.findByUserId(userId);
        
        if (ret == null) {
            
            ret = new UserCampaignPath();
            ret.setUserId(userId);
            
            CampaignPath cp = campaignPathClient.getInitialPath().getData();
            
            ret.setCampaignPathId(cp.getId());
            
            return userCampaignPathRepository.save(ret);
        } else {
            return ret;
        }
        
    }
    
    public void resetPath(String userId) {
        
        UserCampaignPath ucp = findByUserId(userId);
        
        ucp.setFormationId(null);
        
        userCampaignPathRepository.save(ucp);
        
    }
    
    public Battle battle(String userId) {
        return battle(null, userId);
    }
    
    public Battle battle(String formationId, String userId) {
        
        Formation f = formationClient.findByUserIdAndFormationAllocation(userId, FormationAllocation.PVE.toString()).getData();
        
        if (f == null) {
            throw new ValidationException("pve.formation.not.found");
        }
        
        UserCampaignPath ucp = findByUserId(userId);
        
        CampaignPath cp = campaignPathClient.findById(ucp.getCampaignPathId()).getData();
        
        BattleField bf = battleFieldClient.findById(cp.getBattleFieldId()).getData();
        
        if (bf == null) {
            throw new ValidationException("battle.field.not.found");
        }
        
        if (ucp.getFormationId() == null) {
            if (bf.isInitialSite(formationId)) {
                ucp.setFormationId(formationId);
                ucp = userCampaignPathRepository.save(ucp);
            } else {
                throw new ValidationException("invalid.initial.formation");
            }
        } else if (formationId != null) {
            throw new ValidationException("reset.your.path.first");
        }
        
        Battle b = battleClient.doBattle(f.getId(), ucp.getFormationId()).getData();
        
        if (b.isFormationAttackWinner()) {
            
            BattleSite nextSite = bf.getNextSite(ucp.getFormationId());
            
            if (nextSite == null) {
                ucp.setCampaignPathId(cp.getNextCampaignPathId());
                ucp.setFormationId(null);
                userCampaignPathRepository.save(ucp);
                
                if (cp.getReward() != null) {
                    
                    Mail m = new Mail();
                    
                    m.setToUserId(userId);
                    m.setReward(cp.getReward());
                    m.setText("congratulations.you.finish.this.path");
                    
                    mailClient.sendPrivateInternalMail(m);
                    
                }
                
            } else {
                ucp.setFormationId(nextSite.getFormationId());
                userCampaignPathRepository.save(ucp);
            }
        }
        
        return b;
        
    }
}
